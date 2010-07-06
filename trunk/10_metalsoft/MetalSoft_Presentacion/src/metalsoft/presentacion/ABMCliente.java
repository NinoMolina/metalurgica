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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import metalsoft.negocio.ItemCombo;
import metalsoft.negocio.compras.Responsable;
import metalsoft.negocio.gestores.GestorCliente;
import metalsoft.negocio.rrhh.Domicilio;
import metalsoft.negocio.ventas.Cliente;
import metalsoft.negocio.ventas.CondicionIva;

/**
 *
 * @author Vicky
 */
public class ABMCliente extends javax.swing.JFrame implements IDomiciliable, IResponsable{
    private GestorCliente gestor;
    private Domicilio domicilioCliente;
    private Domicilio domicilioResponsable;
    private long idDomicilio;
    private long idResponsable;
    private Responsable responsable;
    private Cliente cliente;
    private metalsoft.datos.dbobject.Cliente clienteDB;
    private metalsoft.datos.dbobject.Responsable responsableDB;
    private metalsoft.datos.dbobject.Domicilio domicilioClienteDB,domicilioResponsableDB;

    private long idCliente;
    /** Creates new form ABMCliente */
    public ABMCliente() {
        initComponents();
        gestor=new GestorCliente();
        cargarComboCondIva();
        cargarComboPrioridad();
        cargarComboEstado();
        cargarComboProvincia(beanDomicilioCliente.getCmbProvincia());
        cargarComboProvincia(beanResponsable.getDomicilioResponsable().getCmbProvincia());
        cargarTipoDocumento();
        addListenerCmbProvincia();
        addListenerCmbLocalidad();
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

    private void cmbProvinciaDomResponsableActionPerformed(java.awt.event.ActionEvent evt)
    {
        JComboBox cmbProvincia=beanResponsable.getDomicilioResponsable().getCmbProvincia();
        JComboBox cmbLocalidad=beanResponsable.getDomicilioResponsable().getCmbLocalidad();
        JComboBox cmbBarrio=beanResponsable.getDomicilioResponsable().getCmbBarrio();
        cmbLocalidad.removeAllItems();
        cmbBarrio.removeAllItems();
        if(cmbProvincia.getSelectedIndex()>0)
        {
            String indexString=((ItemCombo)cmbProvincia.getSelectedItem()).getId();
            int index=Integer.parseInt(indexString);
            gestor.buscarLocalidadesDeProvincia(cmbLocalidad, index);
        }
    }
    private void cmbProvinciaActionPerformed(java.awt.event.ActionEvent evt)
    {
        JComboBox cmbProvincia=beanDomicilioCliente.getCmbProvincia();
        JComboBox cmbLocalidad=beanDomicilioCliente.getCmbLocalidad();
        JComboBox cmbBarrio=beanDomicilioCliente.getCmbBarrio();
        cmbLocalidad.removeAllItems();
        cmbBarrio.removeAllItems();
        if(cmbProvincia.getSelectedIndex()>0)
        {
            String indexString=((ItemCombo)cmbProvincia.getSelectedItem()).getId();
            int index=Integer.parseInt(indexString);
            gestor.buscarLocalidadesDeProvincia(cmbLocalidad, index);
        }
    }
    private void cmbLocalidadDomResponsableActionPerformed(java.awt.event.ActionEvent evt) {
        JComboBox cmbLocalidad=beanResponsable.getDomicilioResponsable().getCmbLocalidad();
        JComboBox cmbBarrio=beanResponsable.getDomicilioResponsable().getCmbBarrio();
        cmbBarrio.removeAllItems();
        if(cmbLocalidad.getSelectedIndex()>0)
        {
            String indexString=((ItemCombo)cmbLocalidad.getSelectedItem()).getId();
            int index=Integer.parseInt(indexString);
            gestor.buscarBarriosDeLocalidad(cmbBarrio, index);
        }
    }
    private void cmbLocalidadActionPerformed(java.awt.event.ActionEvent evt) {
        JComboBox cmbLocalidad=beanDomicilioCliente.getCmbLocalidad();
        JComboBox cmbBarrio=beanDomicilioCliente.getCmbBarrio();
        cmbBarrio.removeAllItems();
        if(cmbLocalidad.getSelectedIndex()>0)
        {
            String indexString=((ItemCombo)cmbLocalidad.getSelectedItem()).getId();
            int index=Integer.parseInt(indexString);
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
        txtNroCliente = new javax.swing.JTextField();
        txtRazonSocial = new javax.swing.JTextField();
        txtCUIT = new javax.swing.JTextField();
        txtFechaBaja = new javax.swing.JTextField();
        txtFechaAlta = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        cmbPrioridad = new javax.swing.JComboBox();
        cmbEstado = new javax.swing.JComboBox();
        cmbCondicionIVA = new javax.swing.JComboBox();
        beanDomicilioCliente = new metalsoft.jbcomp.Domicilio();
        beanResponsable = new metalsoft.jbcomp.Responsable();
        btnModificar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        txtMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailActionPerformed(evt);
            }
        });

        beanDomicilioCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio Cliente"));

        beanResponsable.setBorder(javax.swing.BorderFactory.createTitledBorder("Responsable"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel3)
                                .addGap(9, 9, 9)
                                .addComponent(txtRazonSocial))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCUIT)
                                            .addComponent(cmbCondicionIVA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtFechaAlta)
                                            .addComponent(txtFechaBaja, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)))))))
                    .addComponent(beanDomicilioCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(beanResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5)
                    .addComponent(txtFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanDomicilioCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
            .addComponent(beanResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnModificar.setText("Modificar");

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addGap(5, 5, 5)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 317, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNuevo)
                        .addComponent(btnGuardar))
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar)
                        .addComponent(btnSalir)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMailActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtMailActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtFechaAlta.setText(Fecha.fechaActual());
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        long idEstado=Long.parseLong(((ItemCombo)cmbEstado.getSelectedItem()).getId());
        long idCondIva=Long.parseLong(((ItemCombo)cmbCondicionIVA.getSelectedItem()).getId());
        long idPrioridad=Long.parseLong(((ItemCombo)cmbPrioridad.getSelectedItem()).getId());
        long idBarrioCliente=Long.parseLong(((ItemCombo)beanDomicilioCliente.getCmbBarrio().getSelectedItem()).getId());
        long idLocalidadCliente=Long.parseLong(((ItemCombo)beanDomicilioCliente.getCmbLocalidad().getSelectedItem()).getId());
        long idProvinciaCliente=Long.parseLong(((ItemCombo)beanDomicilioCliente.getCmbProvincia().getSelectedItem()).getId());
        long idBarrioResponsable=Long.parseLong(((ItemCombo)beanResponsable.getDomicilioResponsable().getCmbBarrio().getSelectedItem()).getId());
        long idLocalidadResponsable=Long.parseLong(((ItemCombo)beanResponsable.getDomicilioResponsable().getCmbLocalidad().getSelectedItem()).getId());
        long idProvinciaResponsable=Long.parseLong(((ItemCombo)beanResponsable.getDomicilioResponsable().getCmbProvincia().getSelectedItem()).getId());
        long idTipoDocResponsable=Long.parseLong(((ItemCombo)beanResponsable.getCmbTipoDoc().getSelectedItem()).getId());
        String calle=beanDomicilioCliente.getTxtCalle().getText();
        String depto=beanDomicilioCliente.getTxtDepto().getText();
        String nroCalle=beanDomicilioCliente.getTxtNumero().getText();
        String piso=String.valueOf(beanDomicilioCliente.getSldPiso().getValue());
        String torre=beanDomicilioCliente.getTxtTorre().getText();
        domicilioCliente=crearDomicilio(calle,depto,nroCalle,piso,torre);

        String apeResp=beanResponsable.getTxtApellido().getText();
        String emaResp=beanResponsable.getTxtEmail().getText();
        String faxResp=beanResponsable.getTxtFax().getText();
        String nomResp=beanResponsable.getTxtNombre().getText();
        String nrdResp=beanResponsable.getTxtNroDoc().getText();
        String telResp=beanResponsable.getTxtTelefono().getText();
        responsable=crearResponsable(apeResp,emaResp,faxResp,nomResp,nrdResp,telResp);
        calle=beanResponsable.getDomicilioResponsable().getTxtCalle().getText();
        depto=beanResponsable.getDomicilioResponsable().getTxtDepto().getText();
        nroCalle=beanResponsable.getDomicilioResponsable().getTxtNumero().getText();
        piso=String.valueOf(beanResponsable.getDomicilioResponsable().getSldPiso().getValue());
        torre=beanResponsable.getDomicilioResponsable().getTxtTorre().getText();
        domicilioResponsable=crearDomicilio(calle, depto, nroCalle, piso, torre);
        responsable.setDomicilio(domicilioResponsable);

        String cuitCli=txtCUIT.getText();
        String celCli=txtCelular.getText();
        String fecAltaCli=txtFechaAlta.getText();
        String fecBajaCli=txtFechaBaja.getText();
        String mailCli=txtMail.getText();
        String nroCli=txtNroCliente.getText();
        String razonCli=txtRazonSocial.getText();
        String telCli=txtTelefono.getText();
        cliente=crearCliente(cuitCli,celCli,fecAltaCli,fecBajaCli,mailCli,nroCli,razonCli,telCli);
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
        
        idCliente=gestor.registrarCliente(cliente);

        if(idCliente>0)JOptionPane.showMessageDialog(this, "El cliente se guardó correctamente");
        else JOptionPane.showMessageDialog(this, "No se pudo guardar el cliente");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        ABMCliente_Buscar buscar=null;
        try {
            buscar=(ABMCliente_Buscar) JFrameManager.crearVentana(ABMCliente_Buscar.class.getName());
            buscar.setVentanaCliente(this);
            buscar.setGestor(gestor);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMMatriz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMMatriz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private Domicilio crearDomicilio(String calle,String depto,String nroCalle,String piso,String torre)
    {
        Domicilio dom=new Domicilio();
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


    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ABMCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.jbcomp.Domicilio beanDomicilioCliente;
    private metalsoft.jbcomp.Responsable beanResponsable;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
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
    private javax.swing.JTextField txtCUIT;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtFechaAlta;
    private javax.swing.JTextField txtFechaBaja;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtNroCliente;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    public void setDomicilio(Domicilio dom, long id) {
        domicilioCliente=dom;
        this.idDomicilio=id;
        gestor.tomarDomicilioCliente(dom, id);
    }

    public void setDomicilio(Domicilio dom, metalsoft.datos.dbobject.Domicilio domDB) {
        domicilioCliente=dom;
        gestor.tomarDomicilioClienteDB(domDB);
    }

    public void mostrarDatosDomicilio() {

        String prov=domicilioCliente.getBarrio().getLocalidad().getProvincia().getNombre();
        String barrio=domicilioCliente.getBarrio().getNombre();
        String localidad=domicilioCliente.getBarrio().getLocalidad().getNombre();
        String calle=domicilioCliente.getCalle();
        int nro=domicilioCliente.getNumeroCalle();
        int piso=domicilioCliente.getPiso();
        String torre=domicilioCliente.getTorre();
        String numero=String.valueOf(domicilioCliente.getNumeroCalle());
        beanDomicilioCliente.getTxtCalle().setText(calle);
        beanDomicilioCliente.getTxtNumero().setText(String.valueOf(nro));
        beanDomicilioCliente.getTxtTorre().setText(torre);
        beanDomicilioCliente.getSldPiso().setValue(piso);
    }

    public void setResponsable(Responsable respNegocio, long idResponsable) {
        responsable=respNegocio;
        this.idResponsable=idResponsable;
        gestor.tomarResponsableCliente(respNegocio, idResponsable);
    }

    public void setResponsable(Responsable respNegocio, metalsoft.datos.dbobject.Responsable respDB) {
        responsable=respNegocio;
        gestor.tomarResponsableClienteDB(respDB);
    }

    public void mostrarDatosResponsable() {
        String nombre=responsable.getNombre();
        String apellido=responsable.getApellido();
    }

    public void cargarComboProvincia(JComboBox cmb)
    {
        gestor.obtenerProvincias(cmb);
    }

    private Responsable crearResponsable(String apellido,String email,String fax,String nombre,String nroDoc,String telefono) {
        Responsable resp=new Responsable();
        resp.setApellido(apellido);
        resp.setEmail(email);
        resp.setFax(fax);
        resp.setNombre(nombre);
        resp.setNroDocumento(Integer.parseInt(nroDoc));
        resp.setTelefono(telefono);
        return resp;
    }

    private Cliente crearCliente(String cuit,String cel,String fechaAlta,String fechaBaja,String mail,String nroCli, String razon, String tel) {
        Cliente x=new Cliente();
        x.setCUIT(cuit);
        x.setCelular(cel);

        if(fechaAlta.compareTo("")!=0)
            x.setFechaAlta(Fecha.parseToDate(fechaAlta));
        else
            x.setFechaAlta(null);

        if(fechaBaja.compareTo("")!=0)
            x.setFechaBaja(Fecha.parseToDate(fechaBaja));
        else
            x.setFechaBaja(null);

        x.setMail(mail);
        x.setNroCliente(Integer.parseInt(nroCli));
        x.setRazonSocial(razon);
        x.setTelefono(tel);

        return x;
    }

    private void cargarTipoDocumento() {
        gestor.obtenerTipoDocumentos(beanResponsable.getCmbTipoDoc());
    }

    public void clienteSeleccionado() {
        clienteDB=gestor.mostrarDatosCliente(idCliente);
        domicilioClienteDB=gestor.mostrarDatosDomicilioCliente(clienteDB.getDomicilio());
        responsableDB=gestor.mostrarDatosResponsableCliente(clienteDB.getResponsable());
        domicilioResponsableDB=gestor.mostrarDatosDomicilioResponsable(responsableDB.getDomicilio());
        mostrarDatosCliente();
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
        txtFechaAlta.setText(Fecha.parseToString(clienteDB.getFechaalta(),"dd/MM/yyyy"));
        java.sql.Date fechaBaja=clienteDB.getFechabaja();
        if(fechaBaja!=null)
            txtFechaBaja.setText(Fecha.parseToString(clienteDB.getFechabaja(),"dd/MM/yyyy"));
        else
            txtFechaBaja.setText("");
        txtMail.setText(clienteDB.getMail());
        txtNroCliente.setText(String.valueOf(clienteDB.getNrocliente()));
        txtRazonSocial.setText(clienteDB.getRazonsocial());
        txtTelefono.setText(clienteDB.getTelefono());

        setCondicionIvaSeleccionado(clienteDB.getCondicioniva());
        setEstadoSeleccionado(clienteDB.getEstado());
        setPrioridadSeleccionado(clienteDB.getPrioridad());
        setDatosDomicilioCliente(domicilioClienteDB);
    }

    private void setCondicionIvaSeleccionado(long id) {
        int lenght=cmbCondicionIVA.getItemCount();
        ItemCombo item=null;
        for(int i=0;i<lenght;i++)
        {
            item=(ItemCombo)cmbCondicionIVA.getItemAt(i);
            if(Long.parseLong(item.getId())==id)
            {
                cmbCondicionIVA.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setEstadoSeleccionado(long id) {
        int lenght=cmbEstado.getItemCount();
        ItemCombo item=null;
        for(int i=0;i<lenght;i++)
        {
            item=(ItemCombo)cmbEstado.getItemAt(i);
            if(Long.parseLong(item.getId())==id)
            {
                cmbEstado.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setPrioridadSeleccionado(long id) {
        int lenght=cmbPrioridad.getItemCount();
        ItemCombo item=null;
        for(int i=0;i<lenght;i++)
        {
            item=(ItemCombo)cmbPrioridad.getItemAt(i);
            if(Long.parseLong(item.getId())==id)
            {
                cmbPrioridad.setSelectedIndex(i);
                break;
            }
        }
    }

    private void setDatosDomicilioCliente(metalsoft.datos.dbobject.Domicilio domDB) {
        beanDomicilioCliente.getTxtCalle().setText(domDB.getCalle());
        beanDomicilioCliente.getTxtDepto().setText(domDB.getDepto());
        beanDomicilioCliente.getTxtNumero().setText(String.valueOf(domDB.getNumerocalle()));
        beanDomicilioCliente.getTxtTorre().setText(domDB.getTorre());
    }




}
