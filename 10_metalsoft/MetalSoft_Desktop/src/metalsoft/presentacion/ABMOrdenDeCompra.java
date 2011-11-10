/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMOrdenDeCompra.java
 *
 * Created on 21/04/2011, 10:05:43
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.awt.TrayIcon.MessageType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.dbobject.CompraDB;
import metalsoft.datos.jpa.entity.Compra;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.negocio.gestores.GestorCompra;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.gestores.ViewDetalleCompra;
import metalsoft.negocio.gestores.ViewProveedorXMateriaPrima;
import metalsoft.util.Combo;
import metalsoft.util.EnumOpcionesABM;
import metalsoft.util.ItemCombo;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Mariana
 */
public class ABMOrdenDeCompra extends javax.swing.JDialog {

    private ViewProveedorXMateriaPrima view;
    private GestorCompra gestor;
    private LinkedList<ViewDetalleCompra> filas = new LinkedList<ViewDetalleCompra>();
    private EnumOpcionesABM opcion;
    private ArrayList<ViewDetalleCompra> view2;
    private ArrayList<ViewDetalleCompra> arlDetCompraAEliminar;
    private long idOrden = -1;
    private long idEstado = -1;
    private CompraDB compraDB;
    private Compra compra;

    /** Creates new form ABMOrdenDeCompra */
    public ABMOrdenDeCompra() {
        super(Principal.getVtnPrincipal());
        initComponents();
        addListenerBtnNuevo();
        addListenerBtnGuardar();
        addListenerBtnModificar();
        addListenerBtnBuscar();
        addListenerBtnSalir();
        addListenerBtnEliminar();
        this.gestor= new GestorCompra();
        view = new ViewProveedorXMateriaPrima();
        cargarComboMateriaprima();
        cargarComboProveedores();        
        setEnableComponents(false);
        tblDetalleOrden.setModel(new DetalleOrdenTableModel());
        tblDetalleOrden.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblDetalleOrden.updateUI();
        tblDetalleOrden.packAll();
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }

    private void setearTablas() {
        //DETALLE PRODUCTO
        tblDetalleOrden.setModel(new DetalleOrdenTableModel());
        tblDetalleOrden.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleOrden.setShowHorizontalLines(false);
        tblDetalleOrden.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetalleOrden.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        tblDetalleOrden.setHorizontalScrollEnabled(true);
    }

    private void cargarComboMateriaprima() {
        cmbMateriaPrima.removeAllItems();
        view.cargarComboMateriaprima(cmbMateriaPrima);
    }

    private void cargarComboProveedores() {
        cmbProveedor.removeAllItems();
        gestor.cargarComboProveedor(cmbProveedor);
    }

    private void addListenerBtnNuevo() {
        botones.getBtnNuevo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
    }

    private void limpiarTablaDetalleOrden()
    {
        int cantidadFilas = tblDetalleOrden.getRowCount();
        for(int i=0; i<cantidadFilas; i++)
        {
            filas.remove(0);
            tblDetalleOrden.updateUI();
            tblDetalleOrden.packAll();
        }
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        this.cmbMateriaPrima.setSelectedIndex(0);
        this.cmbProveedor.setSelectedIndex(0);
        limpiarTablaDetalleOrden();
        opcion = EnumOpcionesABM.NUEVO;
        int numOrden=Integer.parseInt(gestor.generarNuevoNumeroOrden());
        if (numOrden==0){
            numOrden ++;
        }
        String orden = "" +numOrden;
        this.txtNroOrden.setText(orden);
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
    private List<Proveedor> proveedor;

    public List<Proveedor> getProv() {
        return proveedor;
    }

    public void setProveedor(List<Proveedor> proveedor) {
        this.proveedor = proveedor;
    }

    private Proveedor searchProveedorById(long id) {
        for (Proveedor p : gestor.getProv()) {
            if (p.getIdproveedor() == id) {
                return p;
            }
        }
        return null;
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {        
        boolean validarProveedor = validar();
        if(!validarProveedor)
            return;
        String numero = txtNroOrden.getText();
        long idProv = Long.parseLong(((ItemCombo) cmbProveedor.getSelectedItem()).getId());
        Proveedor Prov = searchProveedorById(idProv);

        gestor.setnumero(numero);
        gestor.setproveedor(Prov);
        gestor.setListaDetalle(filas);
        
        boolean result = false;
        if (opcion == EnumOpcionesABM.NUEVO) {
            result = gestor.registrarOrden();
        }
        if (opcion == EnumOpcionesABM.MODIFICAR) {
            gestor.setIdOrden(idOrden);
            result = gestor.modificarOrden();
        }
        if (result) {
            if(opcion == EnumOpcionesABM.NUEVO)
            {
                JOptionPane.showMessageDialog(this, "Los datos se guardaron correctamente..!", "Guardar", JOptionPane.INFORMATION_MESSAGE);
                limpiarTabla();
            }
            else
                JOptionPane.showMessageDialog(this, "Los datos se modificaron correctamente..!", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            setEnableComponents(false);
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnModificar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Los datos NO se pudieron guardar.", "Guardar", JOptionPane.ERROR_MESSAGE);
        }
        this.txtNroOrden.setText("");
    }

    private void addListenerBtnModificar() {
        botones.getBtnModificar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
    }

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        long idEstadoOrdenSeleccionada = this.getEstado();
        if(idEstadoOrdenSeleccionada != 1){
            JOptionPane.showMessageDialog(this,"El estado de la Orden de Compra no permite su modificación","Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }
        opcion = EnumOpcionesABM.MODIFICAR;
        arlDetCompraAEliminar = new ArrayList<ViewDetalleCompra>();
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
        ABMOrden_Buscar aBMOrden_Buscar = null;

        aBMOrden_Buscar = new ABMOrden_Buscar(this);
        aBMOrden_Buscar.setVentana(this);
        aBMOrden_Buscar.setGestor(gestor);

        JFrameManager.centrarYMostrarVentana(aBMOrden_Buscar);
    }

    private void addListenerBtnSalir() {
        botones.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnEliminar() {
        botones.getBtnEliminar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        long idEstadoOrdenSeleccionada = this.getEstado();
        if(idEstadoOrdenSeleccionada != 1){
            JOptionPane.showMessageDialog(this,"El estado de la Orden de Compra no permite su cancelación","Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JTextField txtMotivo = new JTextField("");
        Object[] obj = {"Motivo", txtMotivo};
        JOptionPane.showMessageDialog(null, obj, "Ingresar Motivo", JOptionPane.INFORMATION_MESSAGE);
        if (JOptionPane.showConfirmDialog(this,"Esta seguro que desea cancelar la orden de compra?") == JOptionPane.OK_OPTION) {
            opcion = EnumOpcionesABM.ELIMINAR;
            idOrden = this.getIdOrden();
            int result = gestor.cancelarOrden(idOrden,txtMotivo.getText());
            if(result != -1){
                JOptionPane.showMessageDialog(this, "La Orden de Compra ha sido cancelada correctamente");
                this.limpiarCampos();
            }
        }
    }

    private void limpiarCampos(){
       this.cmbMateriaPrima.setEnabled(false);
       this.cmbProveedor.setEnabled(false);
       this.txtNroOrden.setText("");
       this.cmbMateriaPrima.setSelectedIndex(0);
       this.cmbProveedor.setSelectedIndex(0);
       limpiarTablaDetalleOrden();
       botones.getBtnEliminar().setEnabled(false);
       botones.getBtnGuardar().setEnabled(false);
       botones.getBtnModificar().setEnabled(false);
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        agregarDetalleCompra();
    }

    private void agregarDetalleCompra()
    {
        long idMateriaprima = Long.parseLong(((ItemCombo) cmbMateriaPrima.getSelectedItem()).getId());
        String nombreMateriaPrima = (((ItemCombo) cmbMateriaPrima.getSelectedItem()).toString());
        JTextField txtCant = new JTextField("1");
        Object[] obj = {"Cantidad", txtCant};

        int result = JOptionPane.showConfirmDialog(null, obj, "Ingresar Cantidad", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            int cant = Integer.parseInt(txtCant.getText());
            agregarFila(idMateriaprima, nombreMateriaPrima, cant);
            tblDetalleOrden.updateUI();
            tblDetalleOrden.packAll();
        }
    }

    public void agregarFila(long idMateriaPrima, String nombreMateriaPrima, int cant) {
        //vector de tipo Object que contiene los datos de una fila
        ViewDetalleCompra datosFila = new ViewDetalleCompra();
        datosFila.setNombreMateriaPrima(nombreMateriaPrima);
        datosFila.setIdMateriaPrima(idMateriaPrima);
        datosFila.setCantidad(cant);
        filas.addLast(datosFila);
    }

    public void agregarFila(ViewDetalleCompra v) {
        filas.addLast(v);
    }

    public void setIdOrden(long id) {
        idOrden = id;
    }

    public long getIdOrden() {
        return idOrden;
    }

    public void setEstado(long idEstado) {
        this.idEstado = idEstado;
    }

    public long getEstado() {
        return idEstado;
    }

    public void ordenSeleccionada() {
        
        compraDB = gestor.buscarCompraDB(idOrden);
        view2 = gestor.viewDetalleCompra(idOrden);
        mostrarDatosCompra();
        setEnableComponents(false);
        botones.getBtnModificar().setEnabled(true);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(true);

    }

        private void mostrarDatosCompra() {
        //seteo los datos de la compra
        this.txtNroOrden.setText("" + compraDB.getNrocompra());
        if (compraDB.getProveedor() < 1) {
            Combo.setItemComboSeleccionado(this.cmbProveedor, -1);
        } else {
            Combo.setItemComboSeleccionado(this.cmbProveedor, compraDB.getProveedor());
        }
        this.setEstado(compraDB.getEstado());
        
        //seteo los datos del detalle de la compra (la tabla)
        filas.clear();
        Iterator i = view2.iterator();
        ViewDetalleCompra v = null;
        while (i.hasNext()) {
            v = (ViewDetalleCompra) i.next();
            agregarFila(v);
        }
        this.tblDetalleOrden.updateUI();
        this.tblDetalleOrden.packAll();
    }

    public boolean validar()
    {
        String prov = cmbProveedor.getSelectedItem().toString();
        if ( prov == ("--Seleccionar--"))
        {
             JOptionPane.showMessageDialog(this, "Debe Ingresar un Proveedor", "Atención", JOptionPane.INFORMATION_MESSAGE);
             return false;
        }
        return true;
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
        txtNroOrden = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmbMateriaPrima = new javax.swing.JComboBox();
        btnAgregarPieza = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleOrden = new org.jdesktop.swingx.JXTable();
        btnQuitar = new javax.swing.JButton();
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
        setTitle("Orden de Compra");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Órden de Compra"));
        jPanel1.setName("Orden de Compra"); // NOI18N

        txtNroOrden.setText("...");
        txtNroOrden.setEnabled(false);

        jLabel1.setText("Nro. de  Órden: ");

        jLabel2.setText("Proveedor: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Materia Prima"));

        btnAgregarPieza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnAgregarPieza.setText("Agregar");
        btnAgregarPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPiezaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(cmbMateriaPrima, 0, 170, Short.MAX_VALUE)
                .addGap(98, 98, 98)
                .addComponent(btnAgregarPieza, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Órden de Compra"));

        tblDetalleOrden.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblDetalleOrden);
        tblDetalleOrden.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botones, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        int selectedRow = tblDetalleOrden.getSelectedRow();
        filas.remove(selectedRow);
        if (opcion == EnumOpcionesABM.MODIFICAR) {
            arlDetCompraAEliminar.add(view2.get(selectedRow));
        }
        tblDetalleOrden.updateUI();
        tblDetalleOrden.packAll();
}//GEN-LAST:event_btnQuitarActionPerformed

    private void btnAgregarPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPiezaActionPerformed
        agregarDetalleCompra();
    }//GEN-LAST:event_btnAgregarPiezaActionPerformed

    private void setEnableComponents(boolean b) {

        this.tblDetalleOrden.setEnabled(b);
        this.cmbMateriaPrima.setEnabled(b);
        this.cmbProveedor.setEnabled(b);
        this.btnQuitar.setEnabled(b);
        this.btnAgregarPieza.setEnabled(b);
    }
    private void limpiarTabla()
    {
        int cantidadFilas = this.tblDetalleOrden.getRowCount();
        for(int i=0; i<cantidadFilas; i++)
        {
            filas.remove(0);
            tblDetalleOrden.updateUI();
            tblDetalleOrden.packAll();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ABMOrdenDeCompra().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JButton btnAgregarPieza;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox cmbMateriaPrima;
    private javax.swing.JComboBox cmbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTable tblDetalleOrden;
    private javax.swing.JTextField txtNroOrden;
    // End of variables declaration//GEN-END:variables

    public class DetalleOrdenTableModel extends AbstractTableModel {

        String[] columnNames = {"Nombre", "Cantidad"};

        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewDetalleCompra view = filas.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return view.getNombreMateriaPrima();
                case 1:
                    return String.valueOf(view.getCantidad());
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
