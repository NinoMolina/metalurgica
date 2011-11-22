/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GenerarDetalleMateriaPrima.java
 *
 * Created on 28/08/2010, 09:25:48
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import jxl.write.DateTime;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.negocio.gestores.GestorDetalleMateriaPrima;
import metalsoft.negocio.gestores.IBuscadorView;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.PiezaXMateriaPrima;
import metalsoft.negocio.gestores.ViewDetallePedidoCotizacion;
import metalsoft.negocio.gestores.ViewDetalleProducto;
import metalsoft.negocio.gestores.ViewMateriaPrima;
import metalsoft.negocio.gestores.ViewPedidoEnListadoProcedimientos;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Nino
 */
public class GenerarDetalleMateriaPrima extends javax.swing.JDialog implements IBuscadorView {

    /** Creates new form GenerarDetalleMateriaPrima */
    private LinkedList<ViewPedidoEnListadoProcedimientos> filasPedidos;
    private LinkedList<ViewDetallePedidoCotizacion> filasDetallePedido;
    private LinkedList<ViewDetalleProducto> filasDetalleProducto;
    private LinkedList<ViewMateriaPrima> filasMateriaPrima;
    private LinkedList<ViewMateriaPrima> filasMateriaPrimaSeleccionada;
    private GestorDetalleMateriaPrima gestor;
    private Timer timer;
    private TableCellRender tcrTblDetallePedido;
    private long idPedidoSeleccionado, idProductoSeleccionado, idPiezaSeleccionada;

    public GenerarDetalleMateriaPrima() {
        super(Principal.getVtnPrincipal());
        initComponents();
        tcrTblDetallePedido = new TableCellRender();
        tblDetallePedido.setDefaultRenderer(Object.class, tcrTblDetallePedido);
        gestor = new GestorDetalleMateriaPrima();
        buscarPedidosCDetalleProcedimientos();
        setearTablas();
        addListeners();
        setEnabledComponents(false);
        tblDetallePedido.updateUI();
        tblDetallePedido.packAll();
        tblDetalleProducto.updateUI();
        tblDetalleProducto.packAll();
        tblMateriaPrima.updateUI();
        tblMateriaPrima.packAll();
        filasMateriaPrimaSeleccionada = new LinkedList<ViewMateriaPrima>();
        tblMateriaPrimaSeleccionada.updateUI();
        tblMateriaPrimaSeleccionada.packAll();
    }

    private void limpiarCampos() {
        if (filasDetallePedido != null) {
            filasDetallePedido.clear();
        }
        if (filasDetalleProducto != null) {
            filasDetalleProducto.clear();
        }
        if (filasMateriaPrima != null) {
            filasMateriaPrima.clear();
        }
        if (filasMateriaPrimaSeleccionada != null) {
            filasMateriaPrimaSeleccionada.clear();
        }
        tblDetallePedido.updateUI();
        tblDetallePedido.packAll();
        tblDetalleProducto.updateUI();
        tblDetalleProducto.packAll();
        tblMateriaPrima.updateUI();
        tblMateriaPrima.packAll();
        tblMateriaPrimaSeleccionada.updateUI();
        tblMateriaPrimaSeleccionada.packAll();
        txtEtapaProduccion.setText("");
        txtNroPedido.setText("");
        txtCliente.setText("");
        txtPrioridad.setText("");
        lblPedidoSeleccionado.setText("...");
        lblPiezaSeleccionada.setText("...");
        lblProductoSeleccionado.setText("...");
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
        tblDetallePedido.setHorizontalScrollEnabled(true);
        /* On dit de surligner une ligne sur deux */
        tblDetallePedido.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        //DETALLE PRODUCTO
        tblDetalleProducto.setModel(new DetalleProductoTableModel());
        tblDetalleProducto.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleProducto.setShowHorizontalLines(false);
        tblDetalleProducto.setShowVerticalLines(false);
        tblDetalleProducto.setHorizontalScrollEnabled(true);
        /* On dit de surligner une ligne sur deux */
        tblDetalleProducto.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));

        tblMateriaPrima.setModel(new MateriaPrimaTableModel());
        tblMateriaPrima.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblMateriaPrima.setShowHorizontalLines(false);
        tblMateriaPrima.setShowVerticalLines(false);
        tblMateriaPrima.setHorizontalScrollEnabled(true);
        /* On dit de surligner une ligne sur deux */
        tblMateriaPrima.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));

        tblMateriaPrimaSeleccionada.setModel(new MateriaPrimaSeleccionadaTableModel());
        tblMateriaPrimaSeleccionada.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblMateriaPrimaSeleccionada.setShowHorizontalLines(false);
        tblMateriaPrimaSeleccionada.setShowVerticalLines(false);
        tblMateriaPrimaSeleccionada.setHorizontalScrollEnabled(true);
        /* On dit de surligner une ligne sur deux */
        tblMateriaPrimaSeleccionada.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
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

    private void addListenerBtnGuardar() {
        beanBtnGuardar.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        boolean result = gestor.guardarMateriaPrimaPiezaPresupuesto();
        if (result) {
            JOptionPane.showMessageDialog(this, "Los datos se guardaron Correctamente..!");
            buscarPedidosCDetalleProcedimientos();
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
        ViewDetalleProducto v = (ViewDetalleProducto) filasDetalleProducto.get(tblDetalleProducto.convertRowIndexToModel(tblDetalleProducto.getSelectedRow()));
        filasMateriaPrima = gestor.obtenerAllMateriaPrima();

        filasMateriaPrimaSeleccionada.clear();

        MateriaprimaDB db = gestor.buscarMateriaPrimaDePieza(v.getIdPieza());
        separarEtapasDeProduccion(db);
        tblMateriaPrima.updateUI();
        tblMateriaPrima.packAll();
        tblMateriaPrimaSeleccionada.updateUI();
        tblMateriaPrimaSeleccionada.packAll();
        tblMateriaPrima.updateUI();
        tblMateriaPrima.packAll();
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
        ViewDetallePedidoCotizacion v = filasDetallePedido.get(tblDetallePedido.convertRowIndexToModel(tblDetallePedido.getSelectedRow()));
        long idPro = v.getIdProducto();
        filasDetalleProducto = gestor.buscarDetalleProducto(idPro);
        tblDetalleProducto.updateUI();
        tblDetalleProducto.packAll();
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
        JTable tbl = beanTblPedidos.getTblPedidos();
        ViewPedidoEnListadoProcedimientos v = filasPedidos.get(tbl.convertRowIndexToModel(tbl.getSelectedRow()));
        long idPed = v.getIdpedido();
        filasDetallePedido = gestor.buscarDetallePedido(idPed);
        tblDetallePedido.updateUI();
        tblDetallePedido.packAll();
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
        if (tblMateriaPrima.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una materia prima!");
            return;
        }
        if (filasMateriaPrimaSeleccionada.isEmpty()) {
            beanAgregarQuitar.getBtnQuitar().setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "La pieza sólo puede tener una Materia Prima.");
            return;
        }
        ViewMateriaPrima v = filasMateriaPrima.remove(tblMateriaPrima.getSelectedRow());
        filasMateriaPrimaSeleccionada.add(v);
        tblMateriaPrima.updateUI();
        tblMateriaPrima.packAll();
        tblMateriaPrimaSeleccionada.updateUI();
        tblMateriaPrimaSeleccionada.packAll();
        if (filasMateriaPrima.isEmpty()) {
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
        if (filasMateriaPrimaSeleccionada.isEmpty()) {
            return;
        }
        if (filasMateriaPrima.isEmpty()) {
            beanAgregarQuitar.getBtnAgregar().setEnabled(true);
        }
        ViewMateriaPrima v = filasMateriaPrimaSeleccionada.remove(tblMateriaPrimaSeleccionada.getSelectedRow());
        filasMateriaPrima.add(v);
        tblMateriaPrima.updateUI();
        tblMateriaPrima.packAll();
        tblMateriaPrimaSeleccionada.updateUI();
        tblMateriaPrimaSeleccionada.packAll();
        if (filasMateriaPrimaSeleccionada.isEmpty()) {
            beanAgregarQuitar.getBtnQuitar().setEnabled(false);
        }
    }

    private void buscarPedidosCDetalleProcedimientos() {
        filasPedidos = gestor.buscarPedidosCDetalleProcedimientos();
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
        tblMateriaPrima = new org.jdesktop.swingx.JXTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMateriaPrimaSeleccionada = new org.jdesktop.swingx.JXTable();
        beanBtnSalir = new metalsoft.beans.BtnSalirr();
        beanBtnGuardar = new metalsoft.beans.BtnGuardar();
        jLabel14 = new javax.swing.JLabel(){

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
        jPanel8 = new javax.swing.JPanel();
        txtCliente = new javax.swing.JTextField();
        txtNroPedido = new javax.swing.JTextField();
        txtPrioridad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generar Detalle de Materia Prima");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos C/Detalle Procedimientos"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beanTblPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(beanTblPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Pedido"));

        jScrollPane1.setViewportView(tblDetallePedido);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                    .addComponent(beanBtnSeleccionarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
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

        txtEtapaProduccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEtapaProduccionKeyReleased(evt);
            }
        });

        jLabel3.setText("Nombre Materia Prima:");

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

        jScrollPane3.setViewportView(tblMateriaPrima);

        jScrollPane4.setViewportView(tblMateriaPrimaSeleccionada);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEtapaProduccion, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnAsignar)
                                        .addGap(6, 6, 6))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(beanAgregarQuitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(5, 5, 5)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
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
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEtapaProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(beanAgregarQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(btnAsignar)))
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar Datos"));

        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        txtNroPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNroPedidoKeyReleased(evt);
            }
        });

        txtPrioridad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrioridadKeyReleased(evt);
            }
        });

        jLabel6.setText("Nro Pedido:");

        jLabel10.setText("Prioridad:");

        jLabel11.setText("Cliente:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(txtNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel11)
                .addComponent(jLabel10)
                .addComponent(txtPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beanBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 835, Short.MAX_VALUE)
                .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(42, 42, 42))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beanBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void separarEtapasDeProduccion(MateriaprimaDB materiaPrimaDB) {
        ViewMateriaPrima v = null;
        for (int i = 0; i < filasMateriaPrima.size(); i++) {
            v = filasMateriaPrima.get(i);
            if (v.getIdmateriaprima() == materiaPrimaDB.getIdmateriaprima()) {
                filasMateriaPrimaSeleccionada.add(filasMateriaPrima.remove(i));
                break;
            }
        }
    }
    private void txtEtapaProduccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEtapaProduccionKeyReleased
        if (txtEtapaProduccion.getText().compareTo("") != 0) {
            final GenerarDetalleMateriaPrima abm = this;
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
        if (filasMateriaPrimaSeleccionada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay ninguna materia prima para asignar!");
            return;
        }
        PiezaXMateriaPrima pxmp = new PiezaXMateriaPrima();

        ViewDetalleProducto viewDetPro = filasDetalleProducto.get(tblDetalleProducto.getSelectedRow());
        ViewDetallePedidoCotizacion viewDetPed = filasDetallePedido.get(tblDetallePedido.getSelectedRow());
        long idPi = viewDetPro.getIdPieza();
        double anchoPieza = viewDetPro.getAncho();
        double altoPieza = viewDetPro.getAlto();
        double largoPieza = viewDetPro.getLargo();
        long idProd = viewDetPro.getIdProducto();
        double precioProd = viewDetPed.getPrecio();
        int cantProd = viewDetPed.getCantidad();
        long idDetPedido = viewDetPed.getIdDetalle();
        int cantPieza = viewDetPro.getCantidadPieza();
        String nombrePieza = viewDetPro.getNombrePieza();
        String nombreProducto = viewDetPed.getNombreProducto();
        ViewPedidoEnListadoProcedimientos viewPed = filasPedidos.get(beanTblPedidos.getTblPedidos().getSelectedRow());
        long idPed = viewPed.getIdpedido();

        ViewMateriaPrima viewMatPrima = filasMateriaPrimaSeleccionada.get(0);
        double altoMatPrima = viewMatPrima.getAlto();
        double anchoMatPrima = viewMatPrima.getAncho();
        double largoMatPrima = viewMatPrima.getLargo();
        long idMatPrima = viewMatPrima.getIdmateriaprima();

        pxmp.setAltoMatPrima(altoMatPrima);
        pxmp.setAnchoMatPrima(anchoMatPrima);
        pxmp.setLargoMatPrima(largoMatPrima);
        pxmp.setAltoPieza(altoPieza);
        pxmp.setAnchoPieza(anchoPieza);
        pxmp.setLargoPieza(largoPieza);
        pxmp.setIdMateriaPrima(idMatPrima);
        pxmp.setIdPieza(idPi);
        pxmp.setIdProducto(idProd);
        pxmp.setIdDetallePedido(idDetPedido);
        pxmp.setIdPedido(idPed);
        pxmp.setCantidadPieza(cantPieza);
        pxmp.setCantidadProducto(cantProd);
        pxmp.setNombrePieza(nombrePieza);
        pxmp.setNombreProducto(nombreProducto);
        pxmp.setMateriaPrima(viewMatPrima);

        int result = gestor.addPiezaXMateriaPrima(pxmp);
        mostrarMensajeAsignar(result, viewDetPro.getNombrePieza());
        beanBtnGuardar.getBtnGuardar().setEnabled(true);
}//GEN-LAST:event_btnAsignarActionPerformed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        txtNroPedido.setText("");
        txtPrioridad.setText("");
        if (txtCliente.getText().compareTo("") != 0) {
            for (int i=0; i<beanTblPedidos.getTblPedidos().getRowCount(); i++) {
                String cliente=filasPedidos.get(i).getCliente().toLowerCase();
                if(!cliente.contains(txtCliente.getText().toLowerCase())) {
                    filasPedidos.remove(i);
                    i--;
                    beanTblPedidos.getTblPedidos().updateUI();
                }
            }
        }
        if (txtCliente.getText().compareTo("") == 0) {
            buscarPedidosCDetalleProcedimientos();
            beanTblPedidos.getTblPedidos().updateUI();
        }
}//GEN-LAST:event_txtClienteKeyReleased

    private void txtNroPedidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroPedidoKeyReleased
        txtPrioridad.setText("");
        txtCliente.setText("");
        if (txtNroPedido.getText().compareTo("") != 0) {
            for (int i=0; i<beanTblPedidos.getTblPedidos().getRowCount(); i++) {
                int nroPedido=filasPedidos.get(i).getNropedido();
                if(nroPedido != (Integer.parseInt(txtNroPedido.getText()))) {
                    long nro=filasPedidos.get(i).getNropedido();
                    filasPedidos.remove(i);
                    i--;
                    beanTblPedidos.getTblPedidos().updateUI();
                }
            }
        }
        if (txtNroPedido.getText().compareTo("") == 0) {
            buscarPedidosCDetalleProcedimientos();
            beanTblPedidos.getTblPedidos().updateUI();
        }
}//GEN-LAST:event_txtNroPedidoKeyReleased

    private void txtPrioridadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrioridadKeyReleased
        txtNroPedido.setText("");
        txtCliente.setText("");
        if (txtPrioridad.getText().compareTo("") != 0) {
            for (int i=0; i<beanTblPedidos.getTblPedidos().getRowCount(); i++) {
                String prioridad=filasPedidos.get(i).getPrioridad().toLowerCase();
                if(!prioridad.contains(txtPrioridad.getText().toLowerCase())) {
                    filasPedidos.remove(i);
                    i--;
                    beanTblPedidos.getTblPedidos().updateUI();
                }
            }
        }
        if (txtPrioridad.getText().compareTo("") == 0) {
            buscarPedidosCDetalleProcedimientos();
             beanTblPedidos.getTblPedidos().updateUI();

        }
}//GEN-LAST:event_txtPrioridadKeyReleased

    /*
     * 0: no se pudo agregar
     * -1: se agrego correctamente
     * 1: se modifico correctamente
     */
    private void mostrarMensajeAsignar(int result, String nombrePieza) {
        switch (result) {
            case 1:
                JOptionPane.showMessageDialog(this, "Se modificó la pre-asignación de materia prima para la pieza '" + nombrePieza + "'");
                break;
            case 0:
                JOptionPane.showMessageDialog(this, "NO se pudo pre-asignar materia prima para la pieza '" + nombrePieza + "'");
                break;
            case -1:
                JOptionPane.showMessageDialog(this, "Se pre-asignó materia prima para la pieza '" + nombrePieza + "'");
                break;
            default:
                JOptionPane.showMessageDialog(this, "NO se pudo pre-asignar materia prima para la pieza '" + nombrePieza + "'");

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GenerarDetalleMateriaPrima().setVisible(true);
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
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
    private org.jdesktop.swingx.JXTable tblMateriaPrima;
    private org.jdesktop.swingx.JXTable tblMateriaPrimaSeleccionada;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtEtapaProduccion;
    private javax.swing.JTextField txtNroPedido;
    private javax.swing.JTextField txtPrioridad;
    // End of variables declaration//GEN-END:variables

    public JTable getTable(String className) {
        if (className.compareTo(HiloViewEtapaDeProduccion.class.getName()) == 0) {
            return tblMateriaPrima;
        }
        return null;
    }

    public LinkedList getFilas(String className) {
        if (className.compareTo(HiloViewEtapaDeProduccion.class.getName()) == 0) {
            return filasMateriaPrima;
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
            "Descripción",
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

    class MateriaPrimaTableModel extends AbstractTableModel {

        String[] columnNames = {"Nombre",
            "Descripción",
            "Dimensiones",
            "Unidad",
            "Material"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewMateriaPrima view = filasMateriaPrima.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return view.getNombreMateriaPrima();
                case 1:
                    return view.getDescripcion();
                case 2:
                    return "Alto: " + view.getAlto() + "\n Ancho: " + view.getAncho() + "\n Largo: " + view.getLargo();
                case 3:
                    return view.getUnidadmedida();
                case 4:
                    return view.getTipomaterial();
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
            if (filasMateriaPrima != null) {
                return filasMateriaPrima.size();
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

    class MateriaPrimaSeleccionadaTableModel extends AbstractTableModel {

        String[] columnNames = {"Nombre",
            "Descripción",
            "Dimensiones",
            "Unidad",
            "Material"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewMateriaPrima view = filasMateriaPrimaSeleccionada.get(rowIndex);
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return view.getNombreMateriaPrima();
                case 1:
                    return view.getDescripcion();
                case 2:
                    return "Alto: " + view.getAlto() + "\n Ancho: " + view.getAncho() + "\n Largo: " + view.getLargo();
                case 3:
                    return view.getUnidadmedida();
                case 4:
                    return view.getTipomaterial();
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
            if (filasMateriaPrimaSeleccionada != null) {
                return filasMateriaPrimaSeleccionada.size();
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
