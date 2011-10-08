/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMCliente.java
 *
 * Created on May 11, 2010, 12:14:34 AM
 */
package metalsoft.presentacion;

import java.util.Date;
import metalsoft.util.Fecha;
import metalsoft.util.EnumOpcionesABM;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.compras.Responsable;
import metalsoft.negocio.gestores.GestorCliente;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.negocio.rrhh.Domicilio;
import metalsoft.negocio.ventas.Cliente;
import metalsoft.util.Combo;

/**
 *
 * @author Vicky
 */
public class ABMCliente extends javax.swing.JDialog implements IDomiciliable, IResponsable {

    private GestorCliente gestor;
    private Domicilio domicilioCliente;
    private Domicilio domicilioResponsable;
    private long idDomicilio;
    private long idResponsable;
    private Responsable responsable;
    private Cliente cliente;
    private metalsoft.datos.dbobject.ClienteDB clienteDB;
    private metalsoft.datos.dbobject.ResponsableDB responsableDB;
    private metalsoft.datos.dbobject.DomicilioDB domicilioClienteDB, domicilioResponsableDB;
    private EnumOpcionesABM opcion;
    private long idCliente;

    /** Creates new form ABMCliente */
    public ABMCliente() {
        super(Principal.getVtnPrincipal());
        initComponents();
        gestor = new GestorCliente();
        cargarComboCondIva();
        cargarComboPrioridad();
        cargarComboEstado();
        cargarComboProvincia(beanDomicilioCliente.getCmbProvincia());
        cargarComboProvincia(beanResponsable.getDomicilioResponsable().getCmbProvincia());
        cargarTipoDocumento();
        addListeners();
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);

        setEnableComponents(false);
    }

    private void addListeners() {
        addListenerCmbProvincia();
        addListenerCmbLocalidad();
        addListenerBtnNuevo();
        addListenerBtnGuardar();
        addListenerBtnModificar();
        addListenerBtnBuscar();
        addListenerBtnSalir();
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
        long nroCli = gestor.generarNvoNroCliente();
        Combo.setItemComboSeleccionado(cmbEstado, 1);
        Combo.setItemComboSeleccionado(cmbPrioridad, 3);
        lblNroCliente.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_CLIENTE, nroCli));
        txtFechaAlta.setDate(Fecha.fechaActualDate());
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
        ABMCliente_Buscar buscar = null;
        try {
            buscar = (ABMCliente_Buscar) JFrameManager.crearVentana(ABMCliente_Buscar.class.getName());
            buscar.setVentanaCliente(this);
            buscar.setGestor(gestor);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMMatriz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMMatriz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMMatriz.class.getName()).log(Level.SEVERE, null, ex);
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
        if(!esValido()){
            JOptionPane.showMessageDialog(this, "No estan todos los campos completados");
            return;
        }
        long idEstado = Long.parseLong(((ItemCombo) cmbEstado.getSelectedItem()).getId());
        long idCondIva = Long.parseLong(((ItemCombo) cmbCondicionIVA.getSelectedItem()).getId());
        long idPrioridad = Long.parseLong(((ItemCombo) cmbPrioridad.getSelectedItem()).getId());
        long idBarrioCliente = Long.parseLong(((ItemCombo) beanDomicilioCliente.getCmbBarrio().getSelectedItem()).getId());
        long idLocalidadCliente = Long.parseLong(((ItemCombo) beanDomicilioCliente.getCmbLocalidad().getSelectedItem()).getId());
        long idProvinciaCliente = Long.parseLong(((ItemCombo) beanDomicilioCliente.getCmbProvincia().getSelectedItem()).getId());
        long idBarrioResponsable = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbBarrio().getSelectedItem()).getId());
        long idLocalidadResponsable = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbLocalidad().getSelectedItem()).getId());
        long idProvinciaResponsable = Long.parseLong(((ItemCombo) beanResponsable.getDomicilioResponsable().getCmbProvincia().getSelectedItem()).getId());
        long idTipoDocResponsable = Long.parseLong(((ItemCombo) beanResponsable.getCmbTipoDoc().getSelectedItem()).getId());
        String calle = beanDomicilioCliente.getTxtCalle().getText();
        String depto = beanDomicilioCliente.getTxtDepto().getText();
        String nroCalle = beanDomicilioCliente.getTxtNumero().getText();
        String piso = String.valueOf(beanDomicilioCliente.getSldPiso().getValue());
        String torre = beanDomicilioCliente.getTxtTorre().getText();
        domicilioCliente = crearDomicilio(calle, depto, nroCalle, piso, torre);

        String apeResp = beanResponsable.getTxtApellido().getText();
        String emaResp = beanResponsable.getTxtEmail().getText();
        String faxResp = beanResponsable.getTxtFax().getText();
        String nomResp = beanResponsable.getTxtNombre().getText();
        String nrdResp = beanResponsable.getTxtNroDoc().getText();
        String telResp = beanResponsable.getTxtTelefono().getText();
        responsable = crearResponsable(apeResp, emaResp, faxResp, nomResp, nrdResp, telResp);
        calle = beanResponsable.getDomicilioResponsable().getTxtCalle().getText();
        depto = beanResponsable.getDomicilioResponsable().getTxtDepto().getText();
        nroCalle = beanResponsable.getDomicilioResponsable().getTxtNumero().getText();
        piso = String.valueOf(beanResponsable.getDomicilioResponsable().getSldPiso().getValue());
        torre = beanResponsable.getDomicilioResponsable().getTxtTorre().getText();
        domicilioResponsable = crearDomicilio(calle, depto, nroCalle, piso, torre);
        responsable.setDomicilio(domicilioResponsable);

        String cuitCli = txtCUIT.getText();
        String celCli = txtCelular.getText();
        Date fecAltaCli = txtFechaAlta.getDate();
        Date fecBajaCli = txtFechaBaja.getDate();
        String mailCli = txtMail.getText();
        long nro = NumerosAMostrar.getNumeroLong(lblNroCliente.getText());
        String nroCli = String.valueOf(nro);
        String razonCli = txtRazonSocial.getText();
        String telCli = txtTelefono.getText();
        cliente = crearCliente(cuitCli, celCli, fecAltaCli, fecBajaCli, mailCli, nroCli, razonCli, telCli);
        cliente.setDomicilio(domicilioCliente);
        cliente.setResponsable(responsable);

        gestor.setIdBarrioCliente(idBarrioCliente);
        gestor.setIdBarrioResponsable(idBarrioResponsable);
        gestor.setIdLocalidadCliente(idLocalidadCliente);
        gestor.setIdLocalidadResponsable(idLocalidadResponsable);
        gestor.setIdProvinciaCliente(idProvinciaCliente);
        gestor.setIdProvinciaResponsable(idProvinciaResponsable);
        gestor.setIdCondicionIva(idCondIva);
        gestor.setIdEstadoCliente(idEstado);
        gestor.setIdPrioridadCliente(idPrioridad);
        gestor.setIdTipoDocResponsable(idTipoDocResponsable);

        idCliente = -1;

        switch (opcion) {
            case NUEVO:
                idCliente = gestor.registrarCliente(cliente);
                break;
            case MODIFICAR:
                gestor.setIdDomicilioCliente(domicilioClienteDB.getIddomicilio());
                gestor.setIdDomicilioResponsable(domicilioResponsableDB.getIddomicilio());
                gestor.setIdResponsable(responsableDB.getIdresponsable());
                gestor.setIdCliente(clienteDB.getIdcliente());
                idCliente = gestor.modificarCliente(cliente);
                break;
            default:
                break;
        }
        opcion = EnumOpcionesABM.GUARDAR;

        if (idCliente > 0) {
            JOptionPane.showMessageDialog(this, "El cliente se guardó correctamente");
            botones.getBtnGuardar().setEnabled(false);
            botones.getBtnModificar().setEnabled(false);
            botones.getBtnEliminar().setEnabled(false);
            setEnableComponents(false);

        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el cliente");
        }
    }

    private boolean esValido(){
        boolean result=true;
        if(txtRazonSocial.getText().compareTo("")==0)
            result=false;
        if(txtCUIT.getText().compareTo("")==0)
            result=false;
        if(cmbCondicionIVA.getSelectedIndex()<=0)
            result=false;
        if(cmbEstado.getSelectedIndex()<=0)
            result=false;
        if(cmbPrioridad.getSelectedIndex()<=0)
            result=false;
        if(beanDomicilioCliente.getCmbBarrio().getSelectedIndex()<=0)
            result=false;
        if(beanDomicilioCliente.getCmbLocalidad().getSelectedIndex()<=0)
            result=false;
        if(beanDomicilioCliente.getCmbProvincia().getSelectedIndex()<=0)
            result=false;
        if(beanDomicilioCliente.getTxtCalle().getText().compareTo("")==0)
            result=false;
        if(beanDomicilioCliente.getTxtNumero().getText().compareTo("")==0)
            result=false;

        if(beanResponsable.getTxtNombre().getText().compareTo("")==0)
            result=false;
        if(beanResponsable.getTxtApellido().getText().compareTo("")==0)
            result=false;
        if(beanResponsable.getTxtNroDoc().getText().compareTo("")==0)
            result=false;
        if(beanResponsable.getCmbTipoDoc().getSelectedIndex()<=0)
            result=false;
        if(beanResponsable.getDomicilioResponsable().getCmbBarrio().getSelectedIndex()<=0)
            result=false;
        if(beanResponsable.getDomicilioResponsable().getCmbLocalidad().getSelectedIndex()<=0)
            result=false;
        if(beanResponsable.getDomicilioResponsable().getCmbProvincia().getSelectedIndex()<=0)
            result=false;
        if(beanResponsable.getDomicilioResponsable().getTxtCalle().getText().compareTo("")==0)
            result=false;
        if(beanResponsable.getDomicilioResponsable().getTxtNumero().getText().compareTo("")==0)
            result=false;

        return result;
    }

    private void setEnableComponents(boolean b) {
        txtCUIT.setEnabled(b);
        txtCelular.setEnabled(b);
        txtFechaAlta.setEnabled(b);
        txtFechaBaja.setEnabled(b);
        txtMail.setEnabled(b);
        txtRazonSocial.setEnabled(b);
        txtTelefono.setEnabled(b);
        cmbCondicionIVA.setEnabled(b);
        cmbEstado.setEnabled(b);
        cmbPrioridad.setEnabled(b);
        beanDomicilioCliente.setEnabled(b);
        beanResponsable.setEnabled(b);
        beanDomicilioCliente.getBtnAgregarBarrio().setEnabled(b);
        beanDomicilioCliente.getBtnAgregarLocalidad().setEnabled(b);
        beanDomicilioCliente.getBtnAgregarProvincia().setEnabled(b);
        beanResponsable.getDomicilioResponsable().getBtnAgregarBarrio().setEnabled(b);
        beanResponsable.getDomicilioResponsable().getBtnAgregarLocalidad().setEnabled(b);
        beanResponsable.getDomicilioResponsable().getBtnAgregarProvincia().setEnabled(b);
    }

    private void limpiarCampos() {
        txtCUIT.setText("");
        txtCelular.setText("");
        txtFechaAlta.setDate(Fecha.fechaActualDate());
        txtFechaBaja.setDate(null);
        txtMail.setText("");
        lblNroCliente.setText("");
        txtRazonSocial.setText("");
        txtTelefono.setText("");
        cmbCondicionIVA.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
        cmbPrioridad.setSelectedIndex(0);
        beanDomicilioCliente.limpiarCampos();
        beanResponsable.limpiarCampos();
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    private void addListenerCmbProvincia() {
        beanDomicilioCliente.getCmbProvincia().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvinciaActionPerformed(evt);
            }
        });
        beanResponsable.getDomicilioResponsable().getCmbProvincia().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvinciaDomResponsableActionPerformed(evt);
            }
        });
    }

    private void addListenerCmbLocalidad() {
        beanDomicilioCliente.getCmbLocalidad().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLocalidadActionPerformed(evt);
            }
        });
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

    private void cmbProvinciaActionPerformed(java.awt.event.ActionEvent evt) {
        JComboBox cmbProvincia = beanDomicilioCliente.getCmbProvincia();
        JComboBox cmbLocalidad = beanDomicilioCliente.getCmbLocalidad();
        JComboBox cmbBarrio = beanDomicilioCliente.getCmbBarrio();
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

    private void cmbLocalidadActionPerformed(java.awt.event.ActionEvent evt) {
        JComboBox cmbLocalidad = beanDomicilioCliente.getCmbLocalidad();
        JComboBox cmbBarrio = beanDomicilioCliente.getCmbBarrio();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtCUIT = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        cmbPrioridad = new javax.swing.JComboBox();
        cmbEstado = new javax.swing.JComboBox();
        cmbCondicionIVA = new javax.swing.JComboBox();
        beanDomicilioCliente = new metalsoft.beans.Domicilio();
        beanResponsable = new metalsoft.beans.Responsable();
        lblNroCliente = new javax.swing.JLabel();
        txtFechaAlta = new com.toedter.calendar.JDateChooser();
        txtFechaBaja = new com.toedter.calendar.JDateChooser();
        botones = new metalsoft.beans.ABM_Botones();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Clientes");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Administrar Cliente"));

        jLabel1.setText("Nro. Cliente:");

        jLabel2.setText("Condicón IVA:");

        jLabel3.setText("Razón Social:");

        jLabel5.setText("Fecha Alta:");

        jLabel6.setText("CUIT:");

        jLabel7.setText("Prioridad:");

        jLabel8.setText("Estado:");

        jLabel9.setText("Teléfono:");

        jLabel11.setText("Celular:");

        jLabel12.setText("Mail:");

        jLabel13.setText("Fecha Baja:");

        txtRazonSocial.setNextFocusableComponent(cmbPrioridad);

        txtMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailActionPerformed(evt);
            }
        });

        beanDomicilioCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio Cliente"));
        beanDomicilioCliente.setNextFocusableComponent(beanResponsable);

        beanResponsable.setBorder(javax.swing.BorderFactory.createTitledBorder("Responsable"));
        beanResponsable.setNextFocusableComponent(botones);

        lblNroCliente.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblNroCliente.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNroCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmbEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbPrioridad, javax.swing.GroupLayout.Alignment.LEADING, 0, 148, Short.MAX_VALUE)
                                    .addComponent(txtCelular, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                    .addComponent(txtMail, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel3)
                                .addGap(9, 9, 9)
                                .addComponent(txtRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel13))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtFechaBaja, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                            .addComponent(txtFechaAlta, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                            .addComponent(cmbCondicionIVA, 0, 117, Short.MAX_VALUE)
                                            .addComponent(txtCUIT, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))))))
                    .addComponent(beanDomicilioCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(beanResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNroCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cmbPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(cmbCondicionIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel5))
                    .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanDomicilioCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
            .addComponent(beanResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtMailActionPerformed

    private Domicilio crearDomicilio(String calle, String depto, String nroCalle, String piso, String torre) {
        Domicilio dom = new Domicilio();
        dom.setCalle(calle);
        dom.setDepto(depto);
        dom.setNumeroCalle(Integer.parseInt(nroCalle));
        dom.setPiso(Integer.parseInt(piso));
        dom.setTorre(torre);
        return dom;
    }

    private void cargarComboCondIva() {
        cmbCondicionIVA.removeAllItems();
        gestor.buscarCondicionIva(cmbCondicionIVA);
    }

    private void cargarComboPrioridad() {
        cmbPrioridad.removeAllItems();
        gestor.obtenerPrioridades(cmbPrioridad);
    }

    private void cargarComboEstado() {
        cmbEstado.removeAllItems();
        gestor.obtenerEstados(cmbEstado);
    }

    public void setDomicilio(Domicilio dom, long id) {
        domicilioCliente = dom;
        this.idDomicilio = id;
        gestor.tomarDomicilioCliente(dom, id);
    }

    public void setDomicilio(Domicilio dom, metalsoft.datos.dbobject.DomicilioDB domDB) {
        domicilioCliente = dom;
        gestor.tomarDomicilioClienteDB(domDB);
    }

    public void mostrarDatosDomicilio() {

        String prov = domicilioCliente.getBarrio().getLocalidad().getProvincia().getNombre();
        String barrio = domicilioCliente.getBarrio().getNombre();
        String localidad = domicilioCliente.getBarrio().getLocalidad().getNombre();
        String calle = domicilioCliente.getCalle();
        int nro = domicilioCliente.getNumeroCalle();
        int piso = domicilioCliente.getPiso();
        String torre = domicilioCliente.getTorre();
        String numero = String.valueOf(domicilioCliente.getNumeroCalle());
        beanDomicilioCliente.getTxtCalle().setText(calle);
        beanDomicilioCliente.getTxtNumero().setText(String.valueOf(nro));
        beanDomicilioCliente.getTxtTorre().setText(torre);
        beanDomicilioCliente.getSldPiso().setValue(piso);
    }

    public void setResponsable(Responsable respNegocio, long idResponsable) {
        responsable = respNegocio;
        this.idResponsable = idResponsable;
        gestor.tomarResponsableCliente(respNegocio, idResponsable);
    }

    public void setResponsable(Responsable respNegocio, metalsoft.datos.dbobject.ResponsableDB respDB) {
        responsable = respNegocio;
        gestor.tomarResponsableClienteDB(respDB);
    }

    public void mostrarDatosResponsable() {
        String nombre = responsable.getNombre();
        String apellido = responsable.getApellido();
    }

    public void cargarComboProvincia(JComboBox cmb) {
        cmb.removeAllItems();
        gestor.obtenerProvincias(cmb);
    }

    private Responsable crearResponsable(String apellido, String email, String fax, String nombre, String nroDoc, String telefono) {
        Responsable resp = new Responsable();
        resp.setApellido(apellido);
        resp.setEmail(email);
        resp.setFax(fax);
        resp.setNombre(nombre);
        resp.setNroDocumento(Long.parseLong(nroDoc));
        resp.setTelefono(telefono);
        return resp;
    }

    private Cliente crearCliente(String cuit, String cel, Date fechaAlta, Date fechaBaja, String mail, String nroCli, String razon, String tel) {
        Cliente x = new Cliente();
        x.setCUIT(cuit);
        x.setCelular(cel);
        x.setFechaAlta(fechaAlta);
        x.setFechaBaja(fechaBaja);
        x.setMail(mail);
        x.setNroCliente(Integer.parseInt(nroCli));
        x.setRazonSocial(razon);
        x.setTelefono(tel);

        return x;
    }

    private void cargarTipoDocumento() {
        beanResponsable.getCmbTipoDoc().removeAllItems();
        gestor.obtenerTipoDocumentos(beanResponsable.getCmbTipoDoc());
    }

    public void clienteSeleccionado() {
        clienteDB = gestor.buscarClienteDB(idCliente);
        domicilioClienteDB = gestor.buscarDomicilioClienteDB(clienteDB.getDomicilio());
        responsableDB = gestor.buscarResponsableClienteDB(clienteDB.getResponsable());
        domicilioResponsableDB = gestor.buscarDomicilioResponsableDB(responsableDB.getDomicilio());
        mostrarDatosCliente();
        setEnableComponents(false);
        botones.getBtnModificar().setEnabled(true);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(true);
        beanDomicilioCliente.getBtnAgregarBarrio().setEnabled(false);
        beanDomicilioCliente.getBtnAgregarLocalidad().setEnabled(false);
        beanDomicilioCliente.getBtnAgregarProvincia().setEnabled(false);
        beanResponsable.getDomicilioResponsable().getBtnAgregarBarrio().setEnabled(false);
        beanResponsable.getDomicilioResponsable().getBtnAgregarLocalidad().setEnabled(false);
        beanResponsable.getDomicilioResponsable().getBtnAgregarProvincia().setEnabled(false);
    }

    private void mostrarDatosCliente() {
        cargarComboCondIva();
        cargarComboEstado();
        cargarComboPrioridad();
        cargarComboProvincia(beanDomicilioCliente.getCmbProvincia());
        cargarComboProvincia(beanResponsable.getDomicilioResponsable().getCmbProvincia());
//        cargarComboLocalidad(beanDomicilioCliente.getCmbLocalidad());
//        cargarComboLocalidad(beanResponsable.getDomicilioResponsable().getCmbLocalidad());
//        cargarComboBarrio(beanDomicilioCliente.getCmbBarrio());
//        cargarComboBarrio(beanResponsable.getDomicilioResponsable().getCmbBarrio());
        cargarTipoDocumento();
        setDatosCliente();
    }

    private void setDatosCliente() {
        txtCUIT.setText(clienteDB.getCuit());
        txtCelular.setText(clienteDB.getCelular());
        txtFechaAlta.setDate(clienteDB.getFechaalta());
        java.sql.Date fechaBaja = clienteDB.getFechabaja();
        if (fechaBaja != null) {
            txtFechaBaja.setDate(clienteDB.getFechabaja());
        } else {
            txtFechaBaja.setDate(null);
        }
        txtMail.setText(clienteDB.getMail());
        lblNroCliente.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_CLIENTE, clienteDB.getNrocliente()));
        txtRazonSocial.setText(clienteDB.getRazonsocial());
        txtTelefono.setText(clienteDB.getTelefono());

        setItemComboSeleccionado(cmbCondicionIVA, clienteDB.getCondicioniva());
        setItemComboSeleccionado(cmbEstado, clienteDB.getEstado());
        setItemComboSeleccionado(cmbPrioridad, clienteDB.getPrioridad());

        setDatosDomicilio(beanDomicilioCliente, domicilioClienteDB);

        setDatosResponsable(responsableDB, domicilioResponsableDB);
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

    private void setDatosResponsable(metalsoft.datos.dbobject.ResponsableDB respDB, metalsoft.datos.dbobject.DomicilioDB domRespDB) {
        beanResponsable.getTxtApellido().setText(respDB.getApellido());
        beanResponsable.getTxtEmail().setText(respDB.getEmail());
        beanResponsable.getTxtFax().setText(respDB.getFax());
        beanResponsable.getTxtNombre().setText(respDB.getNombre());
        beanResponsable.getTxtNroDoc().setText(String.valueOf(respDB.getNrodocumento()));
        beanResponsable.getTxtTelefono().setText(respDB.getTelefono());

        setItemComboSeleccionado(beanResponsable.getCmbTipoDoc(), respDB.getTipodocumento());

        metalsoft.beans.Domicilio beanDom = beanResponsable.getDomicilioResponsable();
        setDatosDomicilio(beanDom, domRespDB);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ABMCliente().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.Domicilio beanDomicilioCliente;
    private metalsoft.beans.Responsable beanResponsable;
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JComboBox cmbCondicionIVA;
    private javax.swing.JComboBox cmbEstado;
    private javax.swing.JComboBox cmbPrioridad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNroCliente;
    private javax.swing.JTextField txtCUIT;
    private javax.swing.JTextField txtCelular;
    private com.toedter.calendar.JDateChooser txtFechaAlta;
    private com.toedter.calendar.JDateChooser txtFechaBaja;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
