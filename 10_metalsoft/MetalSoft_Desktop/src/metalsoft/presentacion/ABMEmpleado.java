package metalsoft.presentacion;


/*
 * ABMEmpleado.java
 *
 * Created on 05/10/2010, 09:03:12
 */
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import metalsoft.util.EnumOpcionesABM;
import java.util.Iterator;
import javax.swing.JComboBox;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.gestores.GestorEmpleado;
import metalsoft.negocio.gestores.NumerosAMostrar;

import metalsoft.util.Combo;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metalsoft.datos.dbobject.EmpleadoxturnoDB;
import metalsoft.datos.jpa.controller.DomicilioJpaController;
import metalsoft.datos.jpa.controller.EmpleadoJpaController;
import metalsoft.datos.jpa.entity.Domicilio;
import metalsoft.datos.jpa.entity.Empleado;
import metalsoft.util.Fecha;

/**
 *
 * @author Vicky
 */
public class ABMEmpleado extends javax.swing.JDialog {

    private GestorEmpleado gestor;
    private Domicilio domicilioResponsable;
    private long idDomicilio;
    private long idResponsable;
    private Empleado empleado;
    private Empleado empleadoDB;
    private LinkedList turnos;
    private metalsoft.datos.dbobject.DomicilioDB domicilioResponsableDB;
    private metalsoft.datos.dbobject.UsuarioDB usuarioDB;
    private EnumOpcionesABM opcion;
    private long idEmpleado;
    private Map<Integer,String> mapTurnos;
    private final String DEJAR = "dejar";
    private final String AGREGAR = "agregar";
    private final String ELIMINAR = "eliminar";

    /** Creates new form ABMEmpleado */
    public ABMEmpleado() {
        super(Principal.getVtnPrincipal());
        initComponents();
        mapTurnos=new HashMap<Integer, String>();
        gestor = new GestorEmpleado();
        turnos = new LinkedList();
        txtUsuario.setEnabled(false);
        beanResponsable.getLblFax().setVisible(false);
        beanResponsable.getTxtFax().setVisible(false);
        cargarComboCategoria();
        cargarComboCargo();
        cargarComboProvincia(beanResponsable.getDomicilioResponsable().getCmbProvincia());
        cargarTipoDocumento();
        addListeners();
        setEnableComponents(false);
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }

    private void addListeners() {
        addListenerCmbProvincia();
        addListenerCmbLocalidad();
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

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.NUEVO;
        setEnableComponents(true);
        limpiarCampos();
        empleado = new Empleado();
        long nroEmp = gestor.generarNvoNroEmpleado();
        Combo.setItemComboSeleccionado(cmbCategoria, 1);
        chkMañana.setSelected(false);
        chkNoche.setSelected(false);
        chkTarde.setSelected(false);
        lblNroCliente.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_EMPLEADO, nroEmp));
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }

    private void addListenerBtnEliminar() {
        botones.getBtnEliminar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        int result = -1;
        java.sql.Date fechaBaja = new java.sql.Date(Fecha.parseToDate(Fecha.fechaActual(), "dd/MM/yyyy").getTime());
        empleadoDB.setFechaegreso(fechaBaja);
        gestor.setIdEmpleado(idEmpleado);
        result = gestor.bajaEmpleado(empleadoDB);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "El empleado se eliminó correctamente");
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnModificar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(false);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el empleado");
        }
        opcion = EnumOpcionesABM.ELIMINAR;

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

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        opcion = EnumOpcionesABM.MODIFICAR;
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
        ABMEmpleado_Buscar buscar = null;
        try {
            buscar = (ABMEmpleado_Buscar) JFrameManager.crearVentana(ABMEmpleado_Buscar.class.getName());
            buscar.setVentana(this);
            buscar.setGestor(gestor);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

        idEmpleado = -1;

        switch (opcion) {
            case NUEVO:
                empleado = nuevoEmpleado();
                idEmpleado = gestor.guardarEmpleado(empleado,turnos);
                limpiarCampos();
                setEnableComponents(false);
                break;
            case MODIFICAR:
                idEmpleado = modificarEmpleado();
                idEmpleado = gestor.modificarEmpleado(empleado,mapTurnos);
                limpiarCampos();
                setEnableComponents(false);
                break;
            default:
                break;
        }
        opcion = EnumOpcionesABM.GUARDAR;

        if (idEmpleado > 0) {
            JOptionPane.showMessageDialog(this, "El empleado se guardó correctamente");
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnModificar().setEnabled(false);
            botones.getBtnEliminar();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el empleado");
        }
    }

    private void setEnableComponents(boolean b) {
        txtMotivoEgreso.setEnabled(b);
        dccFechaEgreso.setEnabled(b);
        dccFechaIngreso.setEnabled(b);
        jpTurnos.setEnabled(b);
        cmbCargo.setEnabled(b);
        cmbCategoria.setEnabled(b);
        beanResponsable.setEnabled(b);
        chkMañana.setEnabled(b);
        chkNoche.setEnabled(b);
        chkTarde.setEnabled(b);
    }

    private void limpiarCampos() {
        txtMotivoEgreso.setText("");
        txtUsuario.setText("");
        ///hacer lo de las fechas
        dccFechaEgreso.setDate(null);
        dccFechaIngreso.setDate(null);
//        else{
//            GregorianCalendar gc=new GregorianCalendar();
//            gc.setTime(proveedorDB.getFechaalta());
//            dccFechaAlta.setSelectedDate(gc);
//        }
        lblNroCliente.setText("");
        cmbCargo.setSelectedIndex(0);
        cmbCategoria.setSelectedIndex(0);
        chkMañana.setSelected(false);
        chkNoche.setSelected(false);
        chkTarde.setSelected(false);
        mapTurnos.clear();
        beanResponsable.limpiarCampos();
    }

    public long getIdCliente() {
        return idEmpleado;
    }

    public void setIdCliente(long id) {
        this.idEmpleado = id;
    }

    private void addListenerCmbProvincia() {
        beanResponsable.getDomicilioResponsable().getCmbProvincia().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvinciaDomResponsableActionPerformed(evt);
            }
        });
    }

    private void addListenerCmbLocalidad() {
        beanResponsable.getDomicilioResponsable().getCmbLocalidad().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLocalidadDomResponsableActionPerformed(evt);
            }
        });
    }

    private void cmbProvinciaDomResponsableActionPerformed(java.awt.event.ActionEvent evt) {
        JComboBox cmbProvincia = beanResponsable.getDomicilioResponsable().getCmbProvincia();
        JComboBox cmbLocalidad = beanResponsable.getDomicilioResponsable().getCmbLocalidad();
        JComboBox cmbBarrio = beanResponsable.getDomicilioResponsable().getCmbBarrio();
        cmbLocalidad.removeAllItems();
        cmbBarrio.removeAllItems();
        if (cmbProvincia.getSelectedIndex() > 0) {
            String indexString = ((ItemCombo) cmbProvincia.getSelectedItem()).getId();
            int index = Integer.parseInt(indexString);
            gestor.buscarLocalidadesDeProvincia(cmbLocalidad, index);
        }
    }

    private void cmbLocalidadDomResponsableActionPerformed(java.awt.event.ActionEvent evt) {
        JComboBox cmbLocalidad = beanResponsable.getDomicilioResponsable().getCmbLocalidad();
        JComboBox cmbBarrio = beanResponsable.getDomicilioResponsable().getCmbBarrio();
        cmbBarrio.removeAllItems();
        if (cmbLocalidad.getSelectedIndex() > 0) {
            String indexString = ((ItemCombo) cmbLocalidad.getSelectedItem()).getId();
            int index = Integer.parseInt(indexString);
            gestor.buscarBarriosDeLocalidad(cmbBarrio, index);
        }
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
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox();
        beanResponsable = new metalsoft.beans.Responsable();
        lblNroCliente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMotivoEgreso = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbCargo = new javax.swing.JComboBox();
        jpTurnos = new javax.swing.JPanel();
        chkMañana = new javax.swing.JCheckBox();
        chkTarde = new javax.swing.JCheckBox();
        chkNoche = new javax.swing.JCheckBox();
        dccFechaIngreso = new com.toedter.calendar.JDateChooser();
        dccFechaEgreso = new com.toedter.calendar.JDateChooser();
        botones = new metalsoft.beans.ABM_Botones();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Empleado");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Administrar Empleado"));

        jLabel1.setText("Legajo:");

        jLabel5.setText("Fecha Ingreso:");

        jLabel8.setText("Categoria:");

        jLabel13.setText("Fecha Egreso:");

        beanResponsable.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Personales"));

        lblNroCliente.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblNroCliente.setText("...");

        jLabel2.setText("Motivo de Egreso:");

        txtMotivoEgreso.setColumns(20);
        txtMotivoEgreso.setRows(5);
        jScrollPane1.setViewportView(txtMotivoEgreso);

        jLabel3.setText("Usuario:");

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        jLabel4.setText("Cargo:");

        jpTurnos.setBorder(javax.swing.BorderFactory.createTitledBorder("Turnos"));

        chkMañana.setText("Mañana");
        chkMañana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMañanaActionPerformed(evt);
            }
        });

        chkTarde.setText("Tarde");
        chkTarde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTardeActionPerformed(evt);
            }
        });

        chkNoche.setText("Noche");
        chkNoche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNocheActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpTurnosLayout = new javax.swing.GroupLayout(jpTurnos);
        jpTurnos.setLayout(jpTurnosLayout);
        jpTurnosLayout.setHorizontalGroup(
            jpTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTurnosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkMañana)
                .addGap(18, 18, 18)
                .addComponent(chkTarde)
                .addGap(18, 18, 18)
                .addComponent(chkNoche)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jpTurnosLayout.setVerticalGroup(
            jpTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTurnosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpTurnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkMañana)
                    .addComponent(chkTarde)
                    .addComponent(chkNoche)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpTurnos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNroCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbCategoria, javax.swing.GroupLayout.Alignment.LEADING, 0, 239, Short.MAX_VALUE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)))
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(22, 22, 22)
                        .addComponent(cmbCargo, 0, 239, Short.MAX_VALUE))
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dccFechaEgreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dccFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNroCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(dccFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(dccFechaEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(beanResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(botones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtUsuarioActionPerformed

    private void chkTardeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTardeActionPerformed
        // TODO add your handling code here:
        if(chkTarde.isSelected()==true && !mapTurnos.containsKey(2)){
            mapTurnos.put(2,AGREGAR);
        }
        if(chkTarde.isSelected()==false && mapTurnos.containsKey(2))
        {
            mapTurnos.put(2,ELIMINAR);
        }
}//GEN-LAST:event_chkTardeActionPerformed

    private void chkMañanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMañanaActionPerformed
        // TODO add your handling code here:
        if(chkMañana.isSelected()==true && !mapTurnos.containsKey(1)){
            mapTurnos.put(1,AGREGAR);
        }
        if(chkMañana.isSelected()==false && mapTurnos.containsKey(1))
        {
            mapTurnos.put(1,ELIMINAR);
        }
    }//GEN-LAST:event_chkMañanaActionPerformed

    private void chkNocheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNocheActionPerformed
        boolean value = chkNoche.isSelected();
        if(value==true && !mapTurnos.containsKey(3)){
            mapTurnos.put(3,AGREGAR);
        }
        if(value==false && mapTurnos.containsKey(3))
        {
            mapTurnos.put(3,ELIMINAR);
        }
    }//GEN-LAST:event_chkNocheActionPerformed

    private Domicilio crearDomicilio(String calle, String depto, String nroCalle, String piso, String torre) {
        Domicilio dom = new Domicilio();
        dom.setCalle(calle);
        dom.setDepto(depto);
        if (nroCalle.compareTo("") != 0) {
            dom.setNumerocalle(Integer.parseInt(nroCalle));
        }
        if (piso.compareTo("") != 0) {
            dom.setPiso(Integer.parseInt(piso));
        }
        dom.setTorre(torre);
        return dom;
    }

    private void cargarComboCategoria() {
        cmbCategoria.removeAllItems();
        gestor.obtenerCategorias(cmbCategoria);
    }

    private void cargarComboCargo() {
        cmbCargo.removeAllItems();
        gestor.obtenerCargos(cmbCargo);
    }

    public void cargarComboProvincia(JComboBox cmb) {
        cmb.removeAllItems();
        gestor.obtenerProvincias(cmb);
    }

    private void cargarTipoDocumento() {
        beanResponsable.getCmbTipoDoc().removeAllItems();
        gestor.obtenerTipoDocumentos(beanResponsable.getCmbTipoDoc());
    }

    public void empleadoSeleccionado() {
        empleadoDB = gestor.buscarEmpleadoDB(idEmpleado);
        empleado=empleadoDB;
        domicilioResponsableDB = gestor.buscarDomicilioEmpleadoDB(empleadoDB.getDomicilio().getIddomicilio());
        domicilioResponsable=empleado.getDomicilio();
        if (empleadoDB.getUsuario() !=null) {
            usuarioDB = gestor.buscarUsuarioDB(empleadoDB.getUsuario().getIdusuario());
        }
        turnos.clear();
        EmpleadoxturnoDB[] ext = gestor.buscarTurnosDB(idEmpleado);
        for (int i = 0; i < ext.length; i++) {
            turnos.add(ext[i]);
        }
        mostrarDatosEmpleado();
        setEnableComponents(false);
        botones.getBtnModificar().setEnabled(true);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(true);
    }

    private void mostrarDatosEmpleado() {
        cargarComboCargo();
        cargarComboCategoria();
        cargarComboProvincia(beanResponsable.getDomicilioResponsable().getCmbProvincia());
        cargarTipoDocumento();
        if (empleadoDB.getFechaingreso() == null) {
            dccFechaIngreso.setDate(null);
        } else {

            dccFechaIngreso.setDate(empleadoDB.getFechaingreso());
        }

        if (empleadoDB.getFechaegreso() == null) {
            dccFechaEgreso.setDate(null);
        } else {
            dccFechaEgreso.setDate(empleadoDB.getFechaegreso());
        }

        lblNroCliente.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_EMPLEADO, empleadoDB.getLegajo().longValue()));
        txtMotivoEgreso.setText(empleadoDB.getMotivoegreso());
        if(usuarioDB!= null) {
            txtUsuario.setText(usuarioDB.getUsuario());
        }
        if (empleadoDB.getCargo() == null) {
            Combo.setItemComboSeleccionado(cmbCargo, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbCargo, empleadoDB.getCargo().getIdcargo());
        }
        if (empleadoDB.getCategoria() == null) {
            Combo.setItemComboSeleccionado(cmbCategoria, -1);
        } else {
            Combo.setItemComboSeleccionado(cmbCategoria, empleadoDB.getCategoria().getIdcategoria());
        }
        Iterator it = turnos.iterator();

        while (it.hasNext()) {
            switch ((int) ((EmpleadoxturnoDB) it.next()).getIdturno()) {
                case 1:
                    chkMañana.setSelected(true);
                    mapTurnos.put(1, DEJAR);
                    break;
                case 2:
                    chkTarde.setSelected(true);
                    mapTurnos.put(2, DEJAR);
                    break;
                case 3:
                    chkNoche.setSelected(true);
                    mapTurnos.put(3, DEJAR);
                    break;
            }
        }

        setDatosResponsable(empleadoDB, domicilioResponsableDB);
    }

    private void setDatosDomicilio(metalsoft.beans.Domicilio beanDom, metalsoft.datos.dbobject.DomicilioDB domDB) {
        beanDom.getTxtCalle().setText(domDB.getCalle());
        beanDom.getTxtDepto().setText(domDB.getDepto());
        beanDom.getTxtNumero().setText(String.valueOf(domDB.getNumerocalle()));
        beanDom.getTxtTorre().setText(domDB.getTorre());

        JComboBox cmbBarrio = beanDom.getCmbBarrio();
        JComboBox cmbLocalidad = beanDom.getCmbLocalidad();
        JComboBox cmbProvincia = beanDom.getCmbProvincia();

        metalsoft.datos.dbobject.LocalidadDB locDB = gestor.buscarLocalidadDeBarrio(domDB.getBarrio());
        setItemComboSeleccionado(cmbProvincia, locDB.getProvincia());

        setItemComboSeleccionado(cmbLocalidad, locDB.getIdlocalidad());
        setItemComboSeleccionado(cmbBarrio, domDB.getBarrio());
    }

    private void setItemComboSeleccionado(JComboBox cmb, long id) {
        int length = cmb.getItemCount();
        ItemCombo item = null;
        for (int i = 0; i < length; i++) {
            item = (ItemCombo) cmb.getItemAt(i);
            if (Long.parseLong(item.getId()) == id) {
                cmb.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setDatosResponsable(Empleado respDB, metalsoft.datos.dbobject.DomicilioDB domRespDB) {
        beanResponsable.getTxtApellido().setText(respDB.getApellido());
        beanResponsable.getTxtEmail().setText(respDB.getEmail());
        beanResponsable.getTxtNombre().setText(respDB.getNombre());
        beanResponsable.getTxtNroDoc().setText(String.valueOf(respDB.getNrodocumento()));
        beanResponsable.getTxtTelefono().setText(respDB.getTelefono());

        setItemComboSeleccionado(beanResponsable.getCmbTipoDoc(), respDB.getTipodocumento().getIdtipodocumento());

        metalsoft.beans.Domicilio beanDom = beanResponsable.getDomicilioResponsable();
        setDatosDomicilio(beanDom, domRespDB);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ABMEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.Responsable beanResponsable;
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JCheckBox chkMañana;
    private javax.swing.JCheckBox chkNoche;
    private javax.swing.JCheckBox chkTarde;
    private javax.swing.JComboBox cmbCargo;
    private javax.swing.JComboBox cmbCategoria;
    private com.toedter.calendar.JDateChooser dccFechaEgreso;
    private com.toedter.calendar.JDateChooser dccFechaIngreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpTurnos;
    private javax.swing.JLabel lblNroCliente;
    private javax.swing.JTextArea txtMotivoEgreso;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    private Empleado nuevoEmpleado() {
        long idCategoria = Long.parseLong(((ItemCombo) cmbCategoria.getSelectedItem()).getId());
        long idcargo = Long.parseLong(((ItemCombo) cmbCargo.getSelectedItem()).getId());
        empleado=new Empleado();
        if (chkMañana.isSelected() == true) {
            turnos.add((int) 1);
        }
        if (chkTarde.isSelected() == true) {
            turnos.add((int) 2);
        }
        if (chkNoche.isSelected() == true) {
            turnos.add((int) 3);
        }
        long idBarrio = -1;
        if (((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbBarrio().getSelectedItem()) != null) {
            idBarrio = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbBarrio().getSelectedItem()).getId());
        }
        long idLocalidad = -1;
        if (((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbLocalidad().getSelectedItem()) != null) {
            idLocalidad = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbLocalidad().getSelectedItem()).getId());
        }

        long idProvincia = -1;
        if (((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbProvincia().getSelectedItem()) != null) {
            idProvincia = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbProvincia().getSelectedItem()).getId());
        }

        long idTipoDoc = -1;
        if (((ItemCombo) beanResponsable.getCmbTipoDoc().getSelectedItem()) != null) {
            idTipoDoc = Long.parseLong(((ItemCombo) beanResponsable.getCmbTipoDoc().getSelectedItem()).getId());
        }

        //private long cargo;
        int nroDoc = Integer.parseInt(beanResponsable.getTxtNroDoc().getText());
        empleado.setNrodocumento(nroDoc);
        String motivoEgreso = txtMotivoEgreso.getText();
        empleado.setMotivoegreso(motivoEgreso);
        String apeResp = beanResponsable.getTxtApellido().getText();
        empleado.setApellido(apeResp);
        String emaResp = beanResponsable.getTxtEmail().getText();
        empleado.setEmail(emaResp);

        //String faxResp = beanResponsable.getTxtFax().getText();

        String nomResp = beanResponsable.getTxtNombre().getText();
        empleado.setNombre(nomResp);
        String telResp = beanResponsable.getTxtTelefono().getText();
        empleado.setTelefono(telResp);

        String calle = beanResponsable.getDomicilioResponsable().getTxtCalle().getText();
        String depto = beanResponsable.getDomicilioResponsable().getTxtDepto().getText();
        String nroCalle = beanResponsable.getDomicilioResponsable().getTxtNumero().getText();
        String piso = String.valueOf(beanResponsable.getDomicilioResponsable().getSldPiso().getValue());
        String torre = beanResponsable.getDomicilioResponsable().getTxtTorre().getText();


        Date fechaIngreso = null;
        if (dccFechaIngreso.getDate() != null) {
            fechaIngreso = dccFechaIngreso.getDate();
        }
        Date fechaEgreso = null;
        if (dccFechaEgreso.getDate() != null) {
            fechaEgreso = dccFechaEgreso.getDate();
        }
        empleado.setFechaingreso(fechaIngreso);
        empleado.setFechaegreso(fechaEgreso);
        domicilioResponsable = crearDomicilio(calle, depto, nroCalle, piso, torre);

        long nro = gestor.generarNvoNroEmpleado();
        empleado.setLegajo(BigInteger.valueOf(nro));
        gestor.setIdLocalidad(idLocalidad);
        gestor.setIdProvincia(idProvincia);

        domicilioResponsable.setBarrio(gestor.findBarrio(idBarrio));
        empleado.setDomicilio(domicilioResponsable);
//        empleado.setIdLocalidad(idLocalidad);
//        empleado.setIdProvincia(idProvincia);
        empleado.setCargo(gestor.findCargo(idcargo));
        empleado.setCategoria(gestor.findCategoria(idCategoria));
        empleado.setTipodocumento(gestor.findTipoDoc(idTipoDoc));

        return empleado;
    }

    private long modificarEmpleado() {
        long idCategoria = Long.parseLong(((ItemCombo) cmbCategoria.getSelectedItem()).getId());
        long idcargo = Long.parseLong(((ItemCombo) cmbCargo.getSelectedItem()).getId());
        long idBarrio = -1;
        if (((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbBarrio().getSelectedItem()) != null) {
            idBarrio = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbBarrio().getSelectedItem()).getId());
        }
        long idLocalidad = -1;
        if (((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbLocalidad().getSelectedItem()) != null) {
            idLocalidad = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbLocalidad().getSelectedItem()).getId());
        }

        long idProvincia = -1;
        if (((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbProvincia().getSelectedItem()) != null) {
            idProvincia = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbProvincia().getSelectedItem()).getId());
        }

        long idTipoDoc = -1;
        if (((ItemCombo) beanResponsable.getCmbTipoDoc().getSelectedItem()) != null) {
            idTipoDoc = Long.parseLong(((ItemCombo) beanResponsable.getCmbTipoDoc().getSelectedItem()).getId());
        }

        //private long cargo;
        int nroDoc = Integer.parseInt(beanResponsable.getTxtNroDoc().getText());
        empleado.setNrodocumento(nroDoc);
        String motivoEgreso = txtMotivoEgreso.getText();
        empleado.setMotivoegreso(motivoEgreso);
        String apeResp = beanResponsable.getTxtApellido().getText();
        empleado.setApellido(apeResp);
        String emaResp = beanResponsable.getTxtEmail().getText();
        empleado.setEmail(emaResp);

        //String faxResp = beanResponsable.getTxtFax().getText();

        String nomResp = beanResponsable.getTxtNombre().getText();
        empleado.setNombre(nomResp);
        String telResp = beanResponsable.getTxtTelefono().getText();
        empleado.setTelefono(telResp);

        String calle = beanResponsable.getDomicilioResponsable().getTxtCalle().getText();
        String depto = beanResponsable.getDomicilioResponsable().getTxtDepto().getText();
        String nroCalle = beanResponsable.getDomicilioResponsable().getTxtNumero().getText();
        String piso = String.valueOf(beanResponsable.getDomicilioResponsable().getSldPiso().getValue());
        String torre = beanResponsable.getDomicilioResponsable().getTxtTorre().getText();


        Date fechaIngreso = null;
        if (dccFechaIngreso.getDate() != null) {
            fechaIngreso = dccFechaIngreso.getDate();
        }
        Date fechaEgreso = null;
        if (dccFechaEgreso.getDate() != null) {
            fechaEgreso = dccFechaEgreso.getDate();
        }
        empleado.setFechaingreso(fechaIngreso);
        empleado.setFechaegreso(fechaEgreso);
        domicilioResponsable.setCalle(calle);
        domicilioResponsable.setDepto(depto);
        domicilioResponsable.setNumerocalle(Integer.valueOf(nroCalle));
        domicilioResponsable.setPiso(Integer.valueOf(piso));
        domicilioResponsable.setTorre(torre);
        domicilioResponsable.setBarrio(gestor.findBarrio(idBarrio));
        empleado.setDomicilio(domicilioResponsable);
        gestor.setIdLocalidad(idLocalidad);
        gestor.setIdProvincia(idProvincia);
        empleado.setCargo(gestor.findCargo(idcargo));
        empleado.setCategoria(gestor.findCategoria(idCategoria));
        empleado.setTipodocumento(gestor.findTipoDoc(idTipoDoc));
        return empleado.getIdempleado();
    }
}
