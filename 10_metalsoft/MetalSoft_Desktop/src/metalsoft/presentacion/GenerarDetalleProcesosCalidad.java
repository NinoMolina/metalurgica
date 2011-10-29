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

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import metalsoft.negocio.gestores.GestorDetalleProcesosCalidad;
import metalsoft.negocio.gestores.IBuscadorView;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.PiezaXProcesosCalidad;
import metalsoft.negocio.gestores.ViewDetallePedidoCotizacion;
import metalsoft.negocio.gestores.ViewDetalleProducto;
import metalsoft.negocio.gestores.ViewPedidoEnListadoProcedimientos;
import metalsoft.negocio.gestores.ViewProcesoCalidad;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class GenerarDetalleProcesosCalidad extends javax.swing.JDialog implements IBuscadorView {

    private LinkedList<ViewPedidoEnListadoProcedimientos> filasPedidos;
    private LinkedList<ViewDetallePedidoCotizacion> filasDetallePedido;
    private LinkedList<ViewDetalleProducto> filasDetalleProducto;
    private LinkedList<ViewProcesoCalidad> filasProcesoCalidad;
    private LinkedList<ViewProcesoCalidad> filasProcesoCalidadSeleccionado;
    private GestorDetalleProcesosCalidad gestor;
    private Timer timer;
    private TableCellRender tcrTblDetallePedido;
    private long idPedidoSeleccionado, idProductoSeleccionado, idPiezaSeleccionada;

    /** Creates new form GenerarListadoProcedimientosCalidad */
    public GenerarDetalleProcesosCalidad() {
        super(Principal.getVtnPrincipal());
        initComponents();
        tcrTblDetallePedido = new TableCellRender();
        tblDetallePedido.setDefaultRenderer(Object.class, tcrTblDetallePedido);
        gestor = new GestorDetalleProcesosCalidad();
        buscarPedidosConDetalleMateriaPrima();
        addListeners();
        setEnabledComponents(false);
        setearTablas();
        tblDetallePedido.updateUI();
        tblDetalleProducto.updateUI();
        tblProcesoCalidad.updateUI();
        filasProcesoCalidadSeleccionado = new LinkedList<ViewProcesoCalidad>();
        tblProcesoCalidadSeleccionado.updateUI();
    }

    private void limpiarCampos() {
        if (filasDetallePedido != null) {
            filasDetallePedido.clear();
        }
        if (filasDetalleProducto != null) {
            filasDetalleProducto.clear();
        }
        if (filasProcesoCalidad != null) {
            filasProcesoCalidad.clear();
        }
        if (filasProcesoCalidadSeleccionado != null) {
            filasProcesoCalidadSeleccionado.clear();
        }
        tblDetallePedido.updateUI();
        tblDetalleProducto.updateUI();
        tblProcesoCalidad.updateUI();
        tblProcesoCalidadSeleccionado.updateUI();
        txtProcesoCalidad.setText("");
        lblPedidoSeleccionado.setText("...");
        lblPiezaSeleccionada.setText("...");
        lblProductoSeleccionado.setText("...");
    }

    private void setEnabledComponents(boolean b) {
        beanAgregarQuitar.setEnabled(b);
        beanBtnGuardar.getBtnGuardar().setEnabled(b);
        beanBtnSeleccionarPieza.setEnabled(b);
        beanBtnSeleccionarProducto.setEnabled(b);
        txtProcesoCalidad.setEnabled(b);
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

        tblProcesoCalidad.setModel(new ProcesoCalidadTableModel());
        tblProcesoCalidad.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblProcesoCalidad.setShowHorizontalLines(false);
        tblProcesoCalidad.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblProcesoCalidad.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));

        tblProcesoCalidadSeleccionado.setModel(new ProcesoCalidadSeleccionadoTableModel());
        tblProcesoCalidadSeleccionado.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblProcesoCalidadSeleccionado.setShowHorizontalLines(false);
        tblProcesoCalidadSeleccionado.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblProcesoCalidadSeleccionado.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
    }

    private void addListenerBtnGuardar() {
        beanBtnGuardar.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        boolean result = gestor.guardarPiezaXProcesoCalidad();
        if (result) {
            JOptionPane.showMessageDialog(this, "Los datos se guardaron Correctamente..!");
            limpiarCampos();
            setEnabledComponents(false);
        } else {
            JOptionPane.showMessageDialog(this, "NO se pudieron guardar los datos de la pieza");
        }
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
        filasProcesoCalidad = gestor.obtenerProcesosCalidad();
        filasProcesoCalidadSeleccionado.clear();
        tblProcesoCalidad.updateUI();
        tblProcesoCalidadSeleccionado.updateUI();
        idPiezaSeleccionada = v.getIdPieza();
        lblPiezaSeleccionada.setText(v.getNombrePieza());
        beanAgregarQuitar.getBtnAgregar().setEnabled(true);
        beanAgregarQuitar.getBtnQuitar().setEnabled(true);
        btnAsignar.setEnabled(true);
        txtProcesoCalidad.setEnabled(true);
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

    private void addListeners() {
        addListenerBtnAgregar();
        addListenerBtnQuitar();
        addListenerBtnSeleccionarPedido();
        addListenerBtnSeleccionarPieza();
        addListenerBtnSeleccionarProducto();
        addListenerBtnGuardar();
        addListenerBtnSalir();
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
        if (tblProcesoCalidad.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proceso de calidad!");
            return;
        }
        int cantProcesos = 1;
//        JTextField txtCant = new JTextField("1");
//        Object[] obj = {"Cantidad", txtCant};
//        int result = JOptionPane.showConfirmDialog(this, obj, "Ingresar Cantidad de Procesos de Calidad", JOptionPane.OK_CANCEL_OPTION);
//        if (result == JOptionPane.OK_OPTION) {
            //cantProcesos = Integer.parseInt(txtCant.getText());
            if (filasProcesoCalidadSeleccionado.isEmpty()) {
                beanAgregarQuitar.getBtnQuitar().setEnabled(true);
            }
            ViewProcesoCalidad v = filasProcesoCalidad.remove(tblProcesoCalidad.getSelectedRow());
            v.setCantProcesos(cantProcesos);
            filasProcesoCalidadSeleccionado.add(v);
            tblProcesoCalidad.updateUI();
            tblProcesoCalidadSeleccionado.updateUI();
            if (filasProcesoCalidad.isEmpty()) {
                beanAgregarQuitar.getBtnAgregar().setEnabled(false);
            }
        //}

    }

    private void addListenerBtnQuitar() {
        beanAgregarQuitar.getBtnQuitar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBeanQuitarActionPerformed(evt);
            }
        });
    }

    private void btnBeanQuitarActionPerformed(java.awt.event.ActionEvent evt) {
        if (filasProcesoCalidadSeleccionado.isEmpty()) {
            return;
        }
        if (filasProcesoCalidad.isEmpty()) {
            beanAgregarQuitar.getBtnAgregar().setEnabled(true);
        }
        ViewProcesoCalidad v = filasProcesoCalidadSeleccionado.remove(tblProcesoCalidadSeleccionado.getSelectedRow());
        filasProcesoCalidad.add(v);
        tblProcesoCalidad.updateUI();
        tblProcesoCalidadSeleccionado.updateUI();
        if (filasProcesoCalidadSeleccionado.isEmpty()) {
            beanAgregarQuitar.getBtnQuitar().setEnabled(false);
        }
    }

    private void buscarPedidosConDetalleMateriaPrima() {
        filasPedidos = gestor.buscarPedidosConDetalleMateriaPrima();
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
        jScrollPane6 = new javax.swing.JScrollPane();
        tblDetallePedido = new org.jdesktop.swingx.JXTable();
        beanBtnSeleccionarProducto = new metalsoft.beans.BtnSeleccionar();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalleProducto = new org.jdesktop.swingx.JXTable();
        beanBtnSeleccionarPieza = new metalsoft.beans.BtnSeleccionar();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblPiezaSeleccionada = new javax.swing.JLabel();
        txtProcesoCalidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblProductoSeleccionado = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblPedidoSeleccionado = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        beanAgregarQuitar = new metalsoft.beans.AgregarQuitar();
        btnAsignar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblProcesoCalidad = new org.jdesktop.swingx.JXTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblProcesoCalidadSeleccionado = new org.jdesktop.swingx.JXTable();
        beanBtnSalir = new metalsoft.beans.BtnSalirr();
        beanBtnGuardar = new metalsoft.beans.BtnGuardar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generar Detalle de Procesos de Calidad");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos Generados No Cotizados"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beanTblPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beanTblPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Pedido"));

        jScrollPane6.setViewportView(tblDetallePedido);

        beanBtnSeleccionarProducto.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                    .addComponent(beanBtnSeleccionarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(beanBtnSeleccionarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Producto"));

        jScrollPane2.setViewportView(tblDetalleProducto);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addComponent(beanBtnSeleccionarPieza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(beanBtnSeleccionarPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jLabel3.setText("Nombre Proceso de Calidad:");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAsignar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(beanAgregarQuitar, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(beanAgregarQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAsignar)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jScrollPane7.setViewportView(tblProcesoCalidad);

        jScrollPane8.setViewportView(tblProcesoCalidadSeleccionado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
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
                        .addGap(302, 302, 302)
                        .addComponent(lblPiezaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProcesoCalidad, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                                .addGap(45, 45, 45))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProcesoCalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, 0, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(beanBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 836, Short.MAX_VALUE)
                        .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beanBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtProcesoCalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcesoCalidadKeyReleased
        if (txtProcesoCalidad.getText().compareTo("") != 0) {
            final GenerarDetalleProcesosCalidad abm = this;
            timer = new Timer();
            timer.schedule(new TimerTask() {

                private HiloViewEtapaDeProduccion hilo;

                @Override
                public void run() {
                    hilo = new HiloViewEtapaDeProduccion();
                    hilo.setClient(abm);
                    hilo.setValor(txtProcesoCalidad.getText());
                    hilo.start();
                }
            }, 1500);
        }
}//GEN-LAST:event_txtProcesoCalidadKeyReleased

    private void txtProcesoCalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProcesoCalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProcesoCalidadActionPerformed

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
        if (filasProcesoCalidadSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay ningún proceso de calidad para asignar!");
            return;
        }
        ViewDetalleProducto viewDetPro = filasDetalleProducto.get(tblDetalleProducto.getSelectedRow());
        ViewDetallePedidoCotizacion viewDetPed = filasDetallePedido.get(tblDetallePedido.getSelectedRow());
        long idPi = viewDetPro.getIdPieza();
//        double ancho=viewDetPro.getAncho();
//        double alto=viewDetPro.getAlto();
//        double largo=viewDetPro.getLargo();
        long idProd = viewDetPro.getIdProducto();
//        double precioProd=viewDetPed.getPrecio();
//        int cantProd=viewDetPed.getCantidad();
//        int cantPiezas=viewDetPro.getCantidadPieza();
        long idDetPedido = viewDetPed.getIdDetalle();
        ViewPedidoEnListadoProcedimientos viewPed = filasPedidos.get(beanTblPedidos.getTblPedidos().getSelectedRow());
        long idPed = viewPed.getIdpedido();

        PiezaXProcesosCalidad pxpc = null;
        int result = -1;

        pxpc = new PiezaXProcesosCalidad();
        pxpc.setIdPieza(idPi);
        pxpc.setIdProducto(idProd);
        pxpc.setIdDetallePedido(idDetPedido);
        pxpc.setIdPedido(idPed);
        pxpc.setProcesoCalidad(new LinkedList<ViewProcesoCalidad>(filasProcesoCalidadSeleccionado));
        result = gestor.addPiezaXProcesoCalidad(pxpc);

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
                JOptionPane.showMessageDialog(this, "Se modificó la pre-asignación de Procesos de Calidad para la pieza '" + nombrePieza + "'");
                break;
            case 0:
                JOptionPane.showMessageDialog(this, "NO se pudo pre-asignar Procesos de Calidad para la pieza '" + nombrePieza + "'");
                break;
            case -1:
                JOptionPane.showMessageDialog(this, "Se pre-asignaron Procesos de Calidad para la pieza '" + nombrePieza + "'");
                break;
            default:
                JOptionPane.showMessageDialog(this, "NO se pudo pre-asignar Procesos de Calidad para la pieza '" + nombrePieza + "'");

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
    private metalsoft.beans.BtnGuardar beanBtnGuardar;
    private metalsoft.beans.BtnSalirr beanBtnSalir;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionarPieza;
    private metalsoft.beans.BtnSeleccionar beanBtnSeleccionarProducto;
    private metalsoft.beans.PedidosSinAlgEtapaProd beanTblPedidos;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblPedidoSeleccionado;
    private javax.swing.JLabel lblPiezaSeleccionada;
    private javax.swing.JLabel lblProductoSeleccionado;
    private org.jdesktop.swingx.JXTable tblDetallePedido;
    private org.jdesktop.swingx.JXTable tblDetalleProducto;
    private org.jdesktop.swingx.JXTable tblProcesoCalidad;
    private org.jdesktop.swingx.JXTable tblProcesoCalidadSeleccionado;
    private javax.swing.JTextField txtProcesoCalidad;
    // End of variables declaration//GEN-END:variables

    public JTable getTable(String className) {
        if (className.compareTo(HiloViewEtapaDeProduccion.class.getName()) == 0) {
            return tblProcesoCalidad;
        }
        return null;
    }

    public LinkedList getFilas(String className) {
        if (className.compareTo(HiloViewEtapaDeProduccion.class.getName()) == 0) {
            return filasProcesoCalidad;
        }
        return null;
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

    class ProcesoCalidadTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro",
            "Nombre",
            "Acción",
            "Duración",
            "Herramientas"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewProcesoCalidad view = filasProcesoCalidad.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PROCESO_CALIDAD, view.getNroproceso());
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
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if (filasProcesoCalidad != null) {
                return filasProcesoCalidad.size();
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

    class ProcesoCalidadSeleccionadoTableModel extends AbstractTableModel {

        String[] columnNames = {"Nro",
            "Nombre",
            "Acción",
            "Duración",
            "Herramientas",
            "Cantidad"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewProcesoCalidad view = filasProcesoCalidadSeleccionado.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PROCESO_CALIDAD, view.getNroproceso());
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
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            if (filasProcesoCalidadSeleccionado != null) {
                return filasProcesoCalidadSeleccionado.size();
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
