/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Producto.java
 *
 * Created on 27/07/2010, 09:05:44
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import metalsoft.negocio.gestores.IBuscador;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultTreeCellEditor.DefaultTextField;
import metalsoft.datos.dbobject.PiezaDB;
import metalsoft.datos.dbobject.ProductoDB;
import metalsoft.negocio.gestores.GestorPieza;
import metalsoft.negocio.gestores.GestorProducto;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewDetalleProducto;
import metalsoft.negocio.ventas.Pieza;
import metalsoft.util.EnumOpcionesABM;
import metalsoft.util.ItemCombo;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Nino
 */
public class ABMProducto extends javax.swing.JDialog {

    private GestorProducto gestor;
    private ProductoDB productoDB;
    private ArrayList detalleProductoDB;
    private ArrayList<ViewDetalleProducto> arlDetProdAEliminar;
    private ArrayList<ViewDetalleProducto> view;
    private long idProducto = -1;
    //lista enlazada que contiene las filas de la tabla
//    private LinkedList<Object[]> filas=new LinkedList<Object[]>();
    private LinkedList<ViewDetalleProducto> filas = new LinkedList<ViewDetalleProducto>();
    private EnumOpcionesABM opcion;

    /** Creates new form Producto */
    public ABMProducto() {
        super(Principal.getVtnPrincipal());
        initComponents();
        setearTablas();
        addListenerBtnNuevo();
        addListenerBtnGuardar();
        addListenerBtnModificar();
        addListenerBtnBuscar();
        addListenerBtnSalir();
        setEnableComponents(false);
        gestor = new GestorProducto();
        tblDetalleProducto.setModel(new DetalleProductoTableModel());
        tblDetalleProducto.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblDetalleProducto.updateUI();
        tblDetalleProducto.packAll();
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }

    private void setearTablas() {
        //DETALLE PRODUCTO
        tblDetalleProducto.setModel(new DetalleProductoTableModel());
        tblDetalleProducto.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleProducto.setShowHorizontalLines(false);
        tblDetalleProducto.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetalleProducto.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        tblDetalleProducto.setHorizontalScrollEnabled(true);
    }

    private void addListenerBtnNuevo() {
        botones.getBtnNuevo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.NUEVO;
        String numProducto = NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRODUCTO, gestor.generarNuevoNumeroProducto());
        txtNumero.setText(numProducto);
        setEnableComponents(true);
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }

    private void addListenerBtnGuardar() {
        botones.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        String descripcion = txtDescripcion.getText();
        String nombre = txtNombre.getText();
        String numero = txtNumero.getText();
        String[] vNum = numero.split("-");
        numero = vNum[1];
        String precioUnitario = txtPrecioUnitario.getText();
        gestor.setDescripcionProducto(descripcion);
        gestor.setNombreProducto(nombre);
        gestor.setNumeroProducto(numero);
        gestor.setPrecioUnitarioProducto(precioUnitario);
        gestor.setListaDetalle(filas);
        long result = -1;
        if (opcion == EnumOpcionesABM.NUEVO) {
            result = gestor.registrarProducto();
        }
        if (opcion == EnumOpcionesABM.MODIFICAR) {
            gestor.setDetalleAEliminar(arlDetProdAEliminar);
            gestor.setIdProducto(idProducto);
            result = gestor.modificarProducto();
        }
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Los datos se guardaron correctamente..!", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnModificar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(false);
            limpiar();
        } else {
            JOptionPane.showMessageDialog(this, "Los datos NO se pudieron guardar.", "Guardar", JOptionPane.ERROR_MESSAGE);
        }
    }

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
    private void setListaDetalleGestor() {
        gestor.setListaDetalle(filas);
    }

    private void addListenerBtnModificar() {
        botones.getBtnModificar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.MODIFICAR;
        arlDetProdAEliminar = new ArrayList<ViewDetalleProducto>();
        botones.getBtnModificar().setEnabled(false);
        setEnableComponents(true);
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnModificar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(false);
    }

    private void addListenerBtnBuscar() {
        botones.getBtnBuscar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.BUSCAR;
        ABMProducto_Buscar buscar = null;
        buscar = new ABMProducto_Buscar(this);
        buscar.setVentana(this);
        buscar.setGestor(gestor);

        JFrameManager.centrarYMostrarVentana(buscar);
    }

    private void addListenerBtnSalir() {
        botones.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
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
        btnQuitar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDetalleProducto = new org.jdesktop.swingx.JXTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtPrecioUnitario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtNumero = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        btnAgregarPieza = new javax.swing.JButton();
        btnNuevaPieza = new javax.swing.JButton();
        txtValorBusqueda = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstResultadoBusqueda = new javax.swing.JList();
        botones = new metalsoft.beans.ABM_Botones();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Producto");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Producto"));

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(tblDetalleProducto);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(443, Short.MAX_VALUE)
                .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto"));

        jLabel1.setText("Número:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Precio Unitario: $");

        jLabel4.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        txtNumero.setFont(new java.awt.Font("Tahoma", 1, 11));
        txtNumero.setText("...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))))
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(txtNumero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Pieza"));

        jRadioButton1.setText("Nombre");

        btnAgregarPieza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnAgregarPieza.setText("Agregar");
        btnAgregarPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPiezaActionPerformed(evt);
            }
        });

        btnNuevaPieza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/del1.png"))); // NOI18N
        btnNuevaPieza.setText("Nueva Pieza");
        btnNuevaPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaPiezaActionPerformed(evt);
            }
        });

        txtValorBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorBusquedaKeyReleased(evt);
            }
        });

        jLabel5.setText("Valor de Búsqueda:");

        jScrollPane3.setViewportView(lstResultadoBusqueda);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtValorBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevaPieza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregarPieza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregarPieza)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevaPieza))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtValorBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addComponent(jRadioButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE))))
                .addContainerGap())
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPiezaActionPerformed
        ItemCombo item = (ItemCombo) lstResultadoBusqueda.getSelectedValue();
        long idPieza = Long.parseLong(item.getId());
        Pieza p = gestor.buscarPiezaParaDetalleProducto(idPieza);
        JTextField txtCant = new JTextField("1");
        JTextArea txtDesc = new JTextArea("");
        JScrollPane scroll = new JScrollPane(txtDesc);
        Object[] obj = {"Cantidad", txtCant, "Descripcion", scroll};
//        JOptionPane optionPane=new JOptionPane(obj, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
//        JDialog dialog = optionPane.createDialog(this, "Ingresar Cantidad y Descripción");
//        dialog.setVisible(true);
        int result = JOptionPane.showConfirmDialog(null, obj, "Ingresar Cantidad y Descripción", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String cant = txtCant.getText();
            String desc = txtDesc.getText();
            agregarFila(p.getNombre(), desc, Integer.parseInt(cant), p.getAlto(), p.getAncho(), p.getLargo(), p.getTipoMaterial().getNombre(), idPieza, -1, idProducto);
            tblDetalleProducto.updateUI();
            tblDetalleProducto.packAll();
        }

    }//GEN-LAST:event_btnAgregarPiezaActionPerformed

    private void txtValorBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorBusquedaKeyReleased
        if (txtValorBusqueda.getText().compareTo("") != 0) {
            gestor.setListaPiezas(lstResultadoBusqueda);
            gestor.buscarPiezas(txtValorBusqueda.getText());
        }
    }//GEN-LAST:event_txtValorBusquedaKeyReleased

    private void btnNuevaPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaPiezaActionPerformed
        try {
            JFrameManager.crearVentana(ABMPieza.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNuevaPiezaActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        int selectedRow = tblDetalleProducto.getSelectedRow();
        filas.remove(selectedRow);
        if (opcion == EnumOpcionesABM.MODIFICAR) {
            arlDetProdAEliminar.add(view.get(selectedRow));
        }
        tblDetalleProducto.updateUI();
        tblDetalleProducto.packAll();
    }//GEN-LAST:event_btnQuitarActionPerformed

//    public void agregarFila(String pieza,String desc,String cant,String dim,String mat,String idPieza)
//    {
//        //vector de tipo Object que contiene los datos de una fila
//        Object[] datosFila = new Object[6];
//        datosFila[0]=pieza;
//        datosFila[1]=desc;
//        datosFila[2]=cant;
//        datosFila[3]=dim;
//        datosFila[4]=mat;
//        datosFila[5]=idPieza;
//        filas.addLast(datosFila);
//
//    }
    public void agregarFila(String pieza, String desc, int cant, double alto, double ancho, double largo, String mat, long idPieza, long idDet, long idProd) {
        //vector de tipo Object que contiene los datos de una fila
        ViewDetalleProducto datosFila = new ViewDetalleProducto();
        datosFila.setCantidadPieza(cant);
        datosFila.setDescripcion(desc);
        datosFila.setAlto(alto);
        datosFila.setAncho(ancho);
        datosFila.setLargo(largo);
        datosFila.setIdDetalle(idDet);
        datosFila.setIdPieza(idPieza);
        datosFila.setIdProducto(idProd);
        datosFila.setNombrePieza(pieza);
        datosFila.setNombreTipoMaterial(mat);
        filas.addLast(datosFila);
    }

    public void agregarFila(ViewDetalleProducto v) {
        filas.addLast(v);
    }

    public void setIdProducto(long id) {
        idProducto = id;
    }

    public void productoSeleccionado() {
        productoDB = gestor.buscarProductoDB(idProducto);
        view = gestor.viewDetalleProducto(idProducto);
        mostrarDatosProducto();
        setEnableComponents(false);
        botones.getBtnModificar().setEnabled(true);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(true);
    }

    private void mostrarDatosProducto() {
        //seteo los datos del producto
        txtDescripcion.setText(productoDB.getDescripcion());
        txtNombre.setText(productoDB.getNombre());
        txtNumero.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRODUCTO, productoDB.getNroproducto()));
        txtPrecioUnitario.setText(String.valueOf(productoDB.getPreciounitario()));
        //seteo los datos del detalle del producto (la tabla)
        filas.clear();
        Iterator i = view.iterator();
        ViewDetalleProducto v = null;
        while (i.hasNext()) {
            v = (ViewDetalleProducto) i.next();
            agregarFila(v);
        }
        tblDetalleProducto.updateUI();
        tblDetalleProducto.packAll();
    }

    private void setEnableComponents(boolean b) {
        txtDescripcion.setEnabled(b);
        txtNombre.setEnabled(b);
        txtNumero.setEnabled(b);
        txtPrecioUnitario.setEnabled(b);
        txtValorBusqueda.setEnabled(b);
        lstResultadoBusqueda.setEnabled(b);
        tblDetalleProducto.setEnabled(b);
        btnAgregarPieza.setEnabled(b);
        btnNuevaPieza.setEnabled(b);
        btnQuitar.setEnabled(b);
    }
    
        private void limpiar()
    {
        txtDescripcion.setText("");
        txtNombre.setText("");
        txtNumero.setText("");
        txtPrecioUnitario.setText("");
        txtValorBusqueda.setText("");
        lstResultadoBusqueda.setListData(new Vector<Object>());
        limpiarTabla();
        this.jRadioButton1.setSelected(false);
    }
         private void limpiarTabla()
    {
        int cantidadFilas = this.tblDetalleProducto.getRowCount();
        for(int i=0; i<cantidadFilas; i++)
        {
            filas.remove(0);
            tblDetalleProducto.updateUI();
            tblDetalleProducto.packAll();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ABMProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JButton btnAgregarPieza;
    private javax.swing.JButton btnNuevaPieza;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList lstResultadoBusqueda;
    private org.jdesktop.swingx.JXTable tblDetalleProducto;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel txtNumero;
    private javax.swing.JTextField txtPrecioUnitario;
    private javax.swing.JTextField txtValorBusqueda;
    // End of variables declaration//GEN-END:variables


    // End of variables declaration
    // End of variables declaration
    public class DetalleProductoTableModel extends AbstractTableModel {

        String[] columnNames = {"Pieza",
            "Descripcion",
            "Cantidad",
            "Dimensiones",
            "Material"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewDetalleProducto view = filas.get(rowIndex);
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
