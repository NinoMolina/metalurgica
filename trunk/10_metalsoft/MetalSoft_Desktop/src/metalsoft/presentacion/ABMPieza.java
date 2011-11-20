/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMPieza.java
 *
 * Created on 27/06/2010, 20:09:48
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import metalsoft.negocio.gestores.GestorEtapaDeProduccion;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorPieza;
import metalsoft.negocio.gestores.ViewPiezaXEtapa;
import metalsoft.negocio.ventas.EtapaDeProduccion;
import metalsoft.util.Combo;
import metalsoft.util.EnumOpcionesABM;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class ABMPieza extends javax.swing.JDialog {

    private GestorPieza gestorPieza;
    private metalsoft.datos.dbobject.PiezaDB piezaDB;
    private long idPieza = -1;
    private EnumOpcionesABM opcion;
    private ViewPiezaXEtapa gestorPXE;


    private LinkedList<ViewPiezaXEtapa> filas = new LinkedList<ViewPiezaXEtapa>();


    public long getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(long idPieza) {
        this.idPieza = idPieza;
    }

    public JLabel getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(JLabel idpieza) {
        this.idpieza = idpieza;
    }

    public JComboBox getCmbMateriaPrima() {
        return cmbMateriaPrima;
    }

    public void setCmbMateriaPrima(JComboBox cmbMateriaPrima) {
        this.cmbMateriaPrima = cmbMateriaPrima;
    }

    public JComboBox getCmbMatriz() {
        return cmbMatriz;
    }

    public void setCmbMatriz(JComboBox cmbMatriz) {
        this.cmbMatriz = cmbMatriz;
    }

    public JComboBox getCmbTipoMaterial() {
        return cmbTipoMaterial;
    }

    public void setCmbTipoMaterial(JComboBox cmbTipoMaterial) {
        this.cmbTipoMaterial = cmbTipoMaterial;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public metalsoft.datos.dbobject.PiezaDB getPieza() {
        return piezaDB;
    }

    public void setPieza(metalsoft.datos.dbobject.PiezaDB pieza) {
        this.piezaDB = pieza;
    }

    /** Creates new form ABMPieza */
    public ABMPieza() {
        super(Principal.getVtnPrincipal());
        initComponents();
        addListeners();
        gestorPieza = new GestorPieza();
        cargarComboMateriaPrima();
        cargarComboMatriz();
        cargarComboTipoMaterial();
        idpieza.setVisible(false);
        idpieza.setText("");
        setEnableComponents(false);
        this.tblDetallePieza.setModel(new EtapaTableModel());
        tblDetallePieza.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetallePieza.setShowHorizontalLines(false);
        tblDetallePieza.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetallePieza.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        tblDetallePieza.setHorizontalScrollEnabled(true);
        tblDetallePieza.updateUI();
        tblDetallePieza.packAll();
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }

    private void addListeners() {
        addListenerBtnNuevo();
        addListenerBtnGuardar();
        addListenerBtnModificar();
        addListenerBtnBuscar();
        addListenerBtnSalir();
        addListenerBtnEliminar();
    }

    private void addListenerBtnNuevo() {
        botones.getBtnNuevo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
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

    private void addListenerBtnGuardar() {
        botones.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnModificar() {
        botones.getBtnModificar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnBuscar() {
        botones.getBtnBuscar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
    }

    private void addListenerBtnSalir() {
        botones.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        opcion = EnumOpcionesABM.NUEVO;
        limpiar();
        setEnableComponents(true);
        Combo.setItemComboSeleccionado(cmbMatriz, -1);
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }

    public void setEnableComponents(boolean b) {
        cmbMateriaPrima.setEnabled(b);
        cmbMatriz.setEnabled(b);
        cmbTipoMaterial.setEnabled(b);
        dimensiones1.getTxtAlto().setEnabled(b);
        dimensiones1.getTxtAncho().setEnabled(b);
        dimensiones1.getTxtLargo().setEnabled(b);
        txtNombre.setEnabled(b);
        idpieza.setEnabled(b);
        txtValor.setEditable(b);
        this.tblDetallePieza.setEnabled(b);
        this.btnAgregarPieza.setEnabled(b);
        this.btnNuevaPieza.setEnabled(b);
        this.btnQuitar.setEnabled(b);
        this.lstResultadoBusqueda.setEnabled(b);
//        this.jRadioButton1.setEnabled(b);
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        int sol = -1;
        if (opcion == EnumOpcionesABM.NUEVO) {            
            long idTipoMaterial = Long.parseLong(((ItemCombo) cmbTipoMaterial.getSelectedItem()).getId());
            long idMateriaPrima = Long.parseLong(((ItemCombo) cmbMateriaPrima.getSelectedItem()).getId());
            long idMatriz = Long.parseLong(((ItemCombo) cmbMatriz.getSelectedItem()).getId());
            Double alto = Double.parseDouble(dimensiones1.getTxtAlto().getText());
            Double ancho = Double.parseDouble(dimensiones1.getTxtAncho().getText());
            Double largo = Double.parseDouble(dimensiones1.getTxtLargo().getText());
            sol = gestorPieza.guardar(txtNombre.getText(), alto, ancho, largo, idTipoMaterial, idMateriaPrima, idMatriz);
        }

        boolean result = false;
        if (opcion == EnumOpcionesABM.MODIFICAR) {
            if (idpieza.getText().compareTo("") != 0) {
                String indexTipoMaterial = ((ItemCombo) cmbTipoMaterial.getSelectedItem()).getId();
                String indexMateriaPrima = ((ItemCombo) cmbMateriaPrima.getSelectedItem()).getId();
                String indexMatriz = ((ItemCombo) cmbMatriz.getSelectedItem()).getId();
                Double alto = Double.parseDouble(dimensiones1.getTxtAlto().getText());
                Double ancho = Double.parseDouble(dimensiones1.getTxtAncho().getText());
                Double largo = Double.parseDouble(dimensiones1.getTxtLargo().getText());
                result = gestorPieza.modificarPieza(Long.parseLong(idpieza.getText()), txtNombre.getText(), alto, ancho, largo, Long.parseLong(indexTipoMaterial), Long.parseLong(indexMateriaPrima), Long.parseLong(indexMatriz));
            } else {
                JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un pieza primero");
                return;
            }
        }

        //Guardamos Etapas de Produccion:
        boolean resultadoEtapasxPieza = true;
        long idPiezaActualizadaOCreada = 0;
        if( sol != -1 && idpieza.getText().compareTo("") == 0)
            idPiezaActualizadaOCreada = sol;
        else
            idPiezaActualizadaOCreada = Long.parseLong(idpieza.getText());
        for(int i=0; i< filas.size(); i++)
        {
            resultadoEtapasxPieza = gestorPieza.guardarEtapasXPieza(filas.get(i), idPiezaActualizadaOCreada);
            if(!resultadoEtapasxPieza) {
                JOptionPane.showMessageDialog(rootPane, "Los datos NO se pudieron guardar");
                return;
            }
        }
        if ((sol > 0 || result) && resultadoEtapasxPieza) {
            JOptionPane.showMessageDialog(rootPane, "Los datos se guardaron correctamente");
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnModificar().setEnabled(false);
            botones.getBtnEliminar();
            limpiar();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Los datos NO se pudieron guardar");
            return;
        }
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
       // TODO add your handling code here:
        ABMPieza_Buscar buscar = null;
        buscar = new ABMPieza_Buscar(this);
        buscar.setVentana(this);

        JFrameManager.centrarYMostrarVentana(buscar);

    }
    private void limpiar()
    {
        cmbMateriaPrima.setSelectedIndex(0);
        cmbMatriz.setSelectedIndex(0);
        cmbTipoMaterial.setSelectedIndex(0);
        dimensiones1.getTxtAlto().setText("");
        dimensiones1.getTxtAncho().setText("");
        dimensiones1.getTxtLargo().setText("");
        txtNombre.setText("");
        idpieza.setText("");;
        txtValor.setText("");
        limpiarTabla();
        lstResultadoBusqueda.setListData(new Vector<Object>());
//        this.jRadioButton1.setSelected(false);
    }

    private void limpiarTabla()
    {
        int cantidadFilas = tblDetallePieza.getRowCount();
        for(int i=0; i<cantidadFilas; i++)
        {
            filas.remove(0);
            tblDetallePieza.updateUI();
            tblDetallePieza.packAll();
        }
    }


    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        opcion = EnumOpcionesABM.MODIFICAR;
        setEnableComponents(true);
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnModificar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(false);
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        opcion = EnumOpcionesABM.ELIMINAR;
        if (idpieza.getText().compareTo("") != 0) {
            boolean result = gestorPieza.eliminarPieza(Long.parseLong(idpieza.getText()));
            if (result == true) {
                JOptionPane.showMessageDialog(rootPane, "Se ha eliminado la pieza");
                botones.getBtnGuardar().setEnabled(false);
                botones.getBtnModificar().setEnabled(false);
                botones.getBtnEliminar().setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(rootPane, "La pieza NO ha podido ser eliminada");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un pieza primero (buscarla)");
        }
        limpiar();
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    public void cargarComboMateriaPrima() {
        gestorPieza.obtenerMateriaPrima(cmbMateriaPrima);
    }

    public void cargarComboMatriz() {
        gestorPieza.obtenerMatriz(cmbMatriz);
    }

    public void cargarComboTipoMaterial() {
        gestorPieza.obtenerTipoMaterial(cmbTipoMaterial);
    }

    public void piezaSeleccionada() {
        piezaDB = gestorPieza.buscarPieza(idPieza);
        mostrarDatosProducto();
        botones.getBtnModificar().setEnabled(true);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(true);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        cmbTipoMaterial = new javax.swing.JComboBox();
        cmbMateriaPrima = new javax.swing.JComboBox();
        cmbMatriz = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        dimensiones1 = new metalsoft.beans.Dimensiones();
        idpieza = new javax.swing.JLabel();
        botones = new metalsoft.beans.ABM_Botones();
        jPanel3 = new javax.swing.JPanel();
        btnAgregarPieza = new javax.swing.JButton();
        btnNuevaPieza = new javax.swing.JButton();
        txtValor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstResultadoBusqueda = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        btnQuitar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDetallePieza = new org.jdesktop.swingx.JXTable();
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
        setTitle("Administrar Pieza");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pieza"));

        jLabel1.setText("Nombre:");

        jLabel7.setText("Tipo de Material:");

        jLabel8.setText("Materia Prima:");

        jLabel11.setText("Matriz:");

        cmbMatriz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMatrizActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dimensiones"));

        idpieza.setText("jLabel2");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(dimensiones1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 62, Short.MAX_VALUE)
                .add(idpieza)
                .add(66, 66, 66))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dimensiones1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(idpieza))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(25, 25, 25)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1)
                            .add(jLabel7))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(cmbTipoMaterial, 0, 135, Short.MAX_VALUE)
                            .add(txtNombre, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel8)
                            .add(jLabel11))
                        .add(18, 18, 18)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(cmbMateriaPrima, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 146, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(cmbMatriz, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .add(26, 26, 26))
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {cmbMateriaPrima, cmbMatriz}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(txtNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel7)
                            .add(cmbTipoMaterial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel8)
                            .add(cmbMateriaPrima, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel11)
                            .add(cmbMatriz, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 11, Short.MAX_VALUE)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Etapas"));

        btnAgregarPieza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar.png"))); // NOI18N
        btnAgregarPieza.setText("Agregar");
        btnAgregarPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPiezaActionPerformed(evt);
            }
        });

        btnNuevaPieza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/new1.png"))); // NOI18N
        btnNuevaPieza.setText("Nueva Etapa");
        btnNuevaPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaPiezaActionPerformed(evt);
            }
        });

        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorKeyReleased(evt);
            }
        });

        jLabel5.setText("Nombre Etapa:");

        jScrollPane3.setViewportView(lstResultadoBusqueda);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(99, 99, 99)
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(txtValor, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
                .add(18, 18, 18)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(btnAgregarPieza, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(btnNuevaPieza))
                .add(31, 31, 31))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtValor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 9, Short.MAX_VALUE)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .add(btnAgregarPieza)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(btnNuevaPieza)
                .add(29, 29, 29))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Pieza"));

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(tblDetallePieza);

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(384, 384, 384)
                        .add(btnQuitar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(btnQuitar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(botones, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .add(jLabel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(7, 7, 7)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(4, 4, 4)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(botones, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMatrizActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMatrizActionPerformed

    private void btnAgregarPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPiezaActionPerformed
        
        ItemCombo item = (ItemCombo) lstResultadoBusqueda.getSelectedValue();
        long idEtapa = Long.parseLong(item.getId());
        EtapaDeProduccion ep = gestorPieza.buscarEtapaParaDetalleProducto(idEtapa);
        agregarFila(idEtapa, idPieza);            
        tblDetallePieza.updateUI();
        tblDetallePieza.packAll();
    }//GEN-LAST:event_btnAgregarPiezaActionPerformed

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

    private void txtValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyReleased
        if (txtValor.getText().compareTo("") != 0) {
            gestorPieza.setListaEtapas(lstResultadoBusqueda);
            gestorPieza.buscarEtapas(txtValor.getText());
        }
}//GEN-LAST:event_txtValorKeyReleased

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        int selectedRow = tblDetallePieza.getSelectedRow();
        filas.remove(selectedRow);
        tblDetallePieza.updateUI();
        tblDetallePieza.packAll();
}//GEN-LAST:event_btnQuitarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ABMPieza().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JButton btnAgregarPieza;
    private javax.swing.JButton btnNuevaPieza;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox cmbMateriaPrima;
    private javax.swing.JComboBox cmbMatriz;
    private javax.swing.JComboBox cmbTipoMaterial;
    private metalsoft.beans.Dimensiones dimensiones1;
    private javax.swing.JLabel idpieza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList lstResultadoBusqueda;
    private org.jdesktop.swingx.JXTable tblDetallePieza;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables

    private void mostrarDatosProducto() {
        txtNombre.setText(piezaDB.getNombre());
        dimensiones1.getTxtAlto().setText(String.valueOf(piezaDB.getAlto()));
        dimensiones1.getTxtAncho().setText(String.valueOf(piezaDB.getAncho()));
        dimensiones1.getTxtLargo().setText(String.valueOf(piezaDB.getLargo()));
        idpieza.setText(String.valueOf(piezaDB.getIdpieza()));

        Combo.setItemComboSeleccionado(cmbTipoMaterial, piezaDB.getTipomaterial());
        Combo.setItemComboSeleccionado(cmbMateriaPrima, piezaDB.getMateriaprima());
        Combo.setItemComboSeleccionado(cmbMatriz, piezaDB.getMatriz());

    }

    public void agregarFila(long idEtapa, long idPieza) {
        //vector de tipo Object que contiene los datos de una fila
        ViewPiezaXEtapa datosFila = new ViewPiezaXEtapa();
        datosFila.setIdPieza(idPieza);
        datosFila.setIdEtapaProduccion(idEtapa);
        filas.addLast(datosFila);
    }

    public class EtapaTableModel extends AbstractTableModel {

        String[] columnNames = {"Orden",
            "Etapa producciòn"};
        
        public Object getValueAt(int rowIndex, int columnIndex) {

            ViewPiezaXEtapa view = filas.get(rowIndex);
            GestorEtapaDeProduccion gestorEtapa=new GestorEtapaDeProduccion();
            //      Object[] df=filas.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return rowIndex+1;
                case 1:
                    return gestorEtapa.buscarEtapaDeProduccionId(view.getIdEtapaProduccion()).getNombre();
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
