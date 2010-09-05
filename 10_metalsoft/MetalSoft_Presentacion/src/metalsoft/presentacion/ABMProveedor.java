/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMProveedor.java
 *
 * Created on May 11, 2010, 2:04:59 AM
 */

package metalsoft.presentacion;
import java.util.Date;
import metalsoft.util.Fecha;
import metalsoft.util.EnumOpcionesABM;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import metalsoft.negocio.compras.Proveedor;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.compras.Responsable;
import metalsoft.negocio.gestores.GestorProveedor;
import metalsoft.negocio.rrhh.Domicilio;
import metalsoft.negocio.compras.Proveedor;
import metalsoft.negocio.ventas.CondicionIva;

/**
 *
 * @author Vicky
 */
public class ABMProveedor extends javax.swing.JFrame {
    private GestorProveedor gestor;
    private Domicilio domicilioCliente;
    private Domicilio domicilioResponsable;
    private long idDomicilio;
    private long idResponsable;
    private Responsable responsable;
    private Proveedor proveedor;
    private metalsoft.datos.dbobject.ProveedorDB proveedorDB;
    private metalsoft.datos.dbobject.ResponsableDB responsableDB;
    private metalsoft.datos.dbobject.DomicilioDB domicilioClienteDB,domicilioResponsableDB;
    private EnumOpcionesABM opcion;

    private long idProveedor;
    /** Creates new form ABMProveedor */
    public ABMProveedor() {
        initComponents();
        gestor=new GestorProveedor();
        cargarComboCondIva();
//        cargarComboPrioridad();
//        cargarComboEstado();
        cargarComboProvincia(beanDomicilioCliente.getCmbProvincia());
        cargarComboProvincia(beanResponsable.getDomicilioResponsable().getCmbProvincia());
        cargarTipoDocumento();
        addListenerCmbProvincia();
        addListenerCmbLocalidad();
        setEnableComponents(false);
    }
    private void setEnableComponents(boolean b)
    {
        txtCUIT.setEnabled(b);
        txtCelular.setEnabled(b);
        dccFechaAlta.setEnabled(b);
        dccFechaBaja.setEnabled(b);
        txtMail.setEnabled(b);
        txtNroCliente.setEnabled(b);
        txtRazonSocial.setEnabled(b);
        txtTelefono.setEnabled(b);
        cmbCondicionIVA.setEnabled(b);
//        cmbEstado.setEnabled(b);
//        cmbPrioridad.setEnabled(b);
        beanDomicilioCliente.setEnabled(b);
        beanResponsable.setEnabled(b);
    }

    private void limpiarCampos()
    {
        txtCUIT.setText("");
        txtCelular.setText("");
        dccFechaAlta.setSelectedDate(Fecha.fechaActualCalendar());
        dccFechaBaja.setSelectedDate(null);
        txtMail.setText("");
        txtNroCliente.setText("");
        txtRazonSocial.setText("");
        txtTelefono.setText("");
        cmbCondicionIVA.setSelectedIndex(0);
//        cmbEstado.setSelectedIndex(0);
//        cmbPrioridad.setSelectedIndex(0);
        beanDomicilioCliente.limpiarCampos();
        beanResponsable.limpiarCampos();
    }
    public long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(long id) {
        this.idProveedor = id;
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
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNroCliente = new javax.swing.JTextField();
        txtRazonSocial = new javax.swing.JTextField();
        txtCUIT = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        cmbCondicionIVA = new javax.swing.JComboBox();
        beanDomicilioCliente = new metalsoft.beans.Domicilio();
        beanResponsable = new metalsoft.beans.Responsable();
        dccFechaAlta = new datechooser.beans.DateChooserCombo();
        dccFechaBaja = new datechooser.beans.DateChooserCombo();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrar Proveedor");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Administrar Proveedor"));

        jLabel1.setText("Nro. Proveedor:");

        jLabel2.setText("Condicón IVA:");

        jLabel3.setText("Razón Social:");

        jLabel5.setText("Fecha Alta:");

        jLabel6.setText("CUIT:");

        jLabel9.setText("Teléfono:");

        jLabel11.setText("Celular:");

        jLabel12.setText("Mail:");

        jLabel13.setText("Fecha Baja:");

        txtMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMailActionPerformed(evt);
            }
        });

        beanDomicilioCliente.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio Proveedor"));

        beanResponsable.setBorder(javax.swing.BorderFactory.createTitledBorder("Responsable"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMail))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCelular))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dccFechaAlta, 0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbCondicionIVA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dccFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(beanDomicilioCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(beanResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(dccFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(txtCUIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(cmbCondicionIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(dccFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(14, 14, 14)
                        .addComponent(beanDomicilioCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                    .addComponent(beanResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
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
        opcion=EnumOpcionesABM.NUEVO;
        setEnableComponents(true);
        limpiarCampos();
        dccFechaAlta.setSelectedDate(Fecha.fechaActualCalendar());
        dccFechaBaja.setSelectedDate(null);
}//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //long idEstado=Long.parseLong(((ItemCombo)cmbEstado.getSelectedItem()).getId());
        long idCondIva=Long.parseLong(((ItemCombo)cmbCondicionIVA.getSelectedItem()).getId());
        //long idPrioridad=Long.parseLong(((ItemCombo)cmbPrioridad.getSelectedItem()).getId());
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
        Date fecAltaCli=null;
        if(dccFechaAlta.getSelectedDate()!=null)
            fecAltaCli=dccFechaAlta.getSelectedDate().getTime();
        Date fecBajaCli=null;
        if(dccFechaBaja.getSelectedDate()!=null)
            fecBajaCli=dccFechaBaja.getSelectedDate().getTime();
        
        String mailCli=txtMail.getText();
        String nroCli=txtNroCliente.getText();
        String razonCli=txtRazonSocial.getText();
        String telCli=txtTelefono.getText();
        proveedor=crearProveedor(cuitCli,celCli,fecAltaCli,fecBajaCli,mailCli,nroCli,razonCli,telCli);
        proveedor.setDomicilio(domicilioCliente);
        proveedor.setResponsable(responsable);

        gestor.setIdBarrioCliente(idBarrioCliente);
        gestor.setIdBarrioResponsable(idBarrioResponsable);
        gestor.setIdLocalidadCliente(idLocalidadCliente);
        gestor.setIdLocalidadResponsable(idLocalidadResponsable);
        gestor.setIdProvinciaCliente(idProvinciaCliente);
        gestor.setIdProvinciaResponsable(idProvinciaResponsable);
        gestor.setIdCondicionIva(idCondIva);
        //gestor.setIdEstadoCliente(idEstado);
        //gestor.setIdPrioridadCliente(idPrioridad);
        gestor.setIdTipoDocResponsable(idTipoDocResponsable);

        idProveedor=-1;

        switch(opcion) {
            case NUEVO:   idProveedor=gestor.registrarProveedor(proveedor);
            break;
            case MODIFICAR: gestor.setIdDomicilioCliente(domicilioClienteDB.getIddomicilio());
            gestor.setIdDomicilioResponsable(domicilioResponsableDB.getIddomicilio());
            gestor.setIdResponsable(responsableDB.getIdresponsable());
            gestor.setIdProveedor(proveedorDB.getIdproveedor());
            idProveedor=gestor.modificarProveedor(proveedor);
            break;
            default:        break;
        }
        opcion=EnumOpcionesABM.GUARDAR;

        if(idProveedor>0)JOptionPane.showMessageDialog(this, "El cliente se guardó correctamente");
        else JOptionPane.showMessageDialog(this, "No se pudo guardar el cliente");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        opcion=EnumOpcionesABM.MODIFICAR;
        setEnableComponents(true);
}//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
//        int result=-1;
//        java.sql.Date fechaBaja=new Date(Fecha.parseToDate(Fecha.fechaActual(),"dd/MM/yyyy").getTime());
//        proveedorDB.setFechabaja(fechaBaja);
//        gestor.setIdProveedor(idProveedor);
//        result=gestor.bajaCliente(proveedorDB);
//        if(result>0)JOptionPane.showMessageDialog(this, "El cliente se guardó correctamente");
//        else JOptionPane.showMessageDialog(this, "No se pudo guardar el cliente");
        opcion=EnumOpcionesABM.ELIMINAR;
}//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        opcion=EnumOpcionesABM.BUSCAR;
        ABMProveedor_Buscar buscar=null;
        try {
            buscar=(ABMProveedor_Buscar) JFrameManager.crearVentana(ABMProveedor_Buscar.class.getName());
            buscar.setVentanaProveedor(this);
            buscar.setGestor(gestor);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
}//GEN-LAST:event_btnSalirActionPerformed

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

//    private void cargarComboPrioridad() {
//        cmbPrioridad.removeAllItems();
//        gestor.obtenerPrioridades(cmbPrioridad);
//    }

//    private void cargarComboEstado() {
//        cmbEstado.removeAllItems();
//        gestor.obtenerEstados(cmbEstado);
//    }

    public void setDomicilio(Domicilio dom, long id) {
        domicilioCliente=dom;
        this.idDomicilio=id;
        gestor.tomarDomicilioProveedor(dom, id);
    }

    public void setDomicilio(Domicilio dom, metalsoft.datos.dbobject.DomicilioDB domDB) {
        domicilioCliente=dom;
        gestor.tomarDomicilioProveedorDB(domDB);
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
        gestor.tomarResponsableProveedor(respNegocio, idResponsable);
    }

    public void setResponsable(Responsable respNegocio, metalsoft.datos.dbobject.ResponsableDB respDB) {
        responsable=respNegocio;
        gestor.tomarResponsableProveedorDB(respDB);
    }

    public void mostrarDatosResponsable() {
        String nombre=responsable.getNombre();
        String apellido=responsable.getApellido();
    }

    public void cargarComboProvincia(JComboBox cmb)
    {
        cmb.removeAllItems();
        gestor.obtenerProvincias(cmb);
    }

    private Responsable crearResponsable(String apellido,String email,String fax,String nombre,String nroDoc,String telefono) {
        Responsable resp=new Responsable();
        resp.setApellido(apellido);
        resp.setEmail(email);
        resp.setFax(fax);
        resp.setNombre(nombre);
        resp.setNroDocumento(Long.parseLong(nroDoc));
        resp.setTelefono(telefono);
        return resp;
    }

    private Proveedor crearProveedor(String cuit,String cel,Date fechaAlta,Date fechaBaja,String mail,String nroProv, String razon, String tel) {
        Proveedor x=new Proveedor();
        x.setCUIT(cuit);
        x.setCelular(cel);

        if(fechaAlta!=null)
            x.setFechaAlta(fechaAlta);
        else
            x.setFechaAlta(null);

        if(fechaBaja!=null)
            x.setFechaBaja(fechaBaja);
        else
            x.setFechaBaja(null);

        x.setMail(mail);
        x.setNroProveedor(Integer.parseInt(nroProv));
        x.setRazonSocial(razon);
        x.setTelefono(tel);

        return x;
    }

    private void cargarTipoDocumento() {
        beanResponsable.getCmbTipoDoc().removeAllItems();
        gestor.obtenerTipoDocumentos(beanResponsable.getCmbTipoDoc());
    }

    public void proveedorSeleccionado() {
        proveedorDB=gestor.buscarProveedorDB(idProveedor);
        domicilioClienteDB=gestor.buscarDomicilioProveedorDB(proveedorDB.getDomicilio());
        responsableDB=gestor.buscarResponsableProveedorDB(proveedorDB.getResponsable());
        domicilioResponsableDB=gestor.buscarDomicilioResponsableDB(responsableDB.getDomicilio());
        mostrarDatosProveedor();
        setEnableComponents(false);
    }

    private void mostrarDatosProveedor() {
        cargarComboCondIva();
//        cargarComboEstado();
//        cargarComboPrioridad();
        cargarComboProvincia(beanDomicilioCliente.getCmbProvincia());
        cargarComboProvincia(beanResponsable.getDomicilioResponsable().getCmbProvincia());
//        cargarComboLocalidad(beanDomicilioCliente.getCmbLocalidad());
//        cargarComboLocalidad(beanResponsable.getDomicilioResponsable().getCmbLocalidad());
//        cargarComboBarrio(beanDomicilioCliente.getCmbBarrio());
//        cargarComboBarrio(beanResponsable.getDomicilioResponsable().getCmbBarrio());
        cargarTipoDocumento();
        setDatosProveedor();
    }

    private void setDatosProveedor() {
        txtCUIT.setText(proveedorDB.getCuit());
        txtCelular.setText(proveedorDB.getCelular());
        if(proveedorDB.getFechaalta()==null)
            dccFechaAlta.setSelectedDate(null);
        else{
            GregorianCalendar gc=new GregorianCalendar();
            gc.setTime(proveedorDB.getFechaalta());
            dccFechaAlta.setSelectedDate(gc);
        }
        if(proveedorDB.getFechabaja()==null)
            dccFechaBaja.setSelectedDate(null);
        else{
            GregorianCalendar gc=new GregorianCalendar();
            gc.setTime(proveedorDB.getFechabaja());
            dccFechaBaja.setSelectedDate(gc);
        }
        
        txtMail.setText(proveedorDB.getMail());
        txtNroCliente.setText(String.valueOf(proveedorDB.getNroproveedor()));
        txtRazonSocial.setText(proveedorDB.getRazonsocial());
        txtTelefono.setText(proveedorDB.getTelefono());

        setItemComboSeleccionado(cmbCondicionIVA,proveedorDB.getCondicion());
//        setItemComboSeleccionado(cmbEstado,proveedorDB.getEstado());
//        setItemComboSeleccionado(cmbPrioridad,proveedorDB.getPrioridad());

        setDatosDomicilio(beanDomicilioCliente,domicilioClienteDB);

        setDatosResponsable(responsableDB,domicilioResponsableDB);
    }

    private void setDatosDomicilio(metalsoft.beans.Domicilio beanDom,metalsoft.datos.dbobject.DomicilioDB domDB) {
        beanDom.getTxtCalle().setText(domDB.getCalle());
        beanDom.getTxtDepto().setText(domDB.getDepto());
        beanDom.getTxtNumero().setText(String.valueOf(domDB.getNumerocalle()));
        beanDom.getTxtTorre().setText(domDB.getTorre());

        JComboBox cmbBarrio=beanDom.getCmbBarrio();
        JComboBox cmbLocalidad=beanDom.getCmbLocalidad();
        JComboBox cmbProvincia=beanDom.getCmbProvincia();

        metalsoft.datos.dbobject.Localidad locDB=gestor.buscarLocalidadDeBarrio(domDB.getBarrio());
        setItemComboSeleccionado(cmbProvincia,locDB.getProvincia());

        setItemComboSeleccionado(cmbLocalidad,locDB.getIdlocalidad());
        setItemComboSeleccionado(cmbBarrio,domDB.getBarrio());
    }

    private void setItemComboSeleccionado(JComboBox cmb, long id) {
        int length=cmb.getItemCount();
        ItemCombo item=null;
        for(int i=0;i<length;i++)
        {
            item=(ItemCombo)cmb.getItemAt(i);
            if(Long.parseLong(item.getId())==id)
            {
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

        metalsoft.beans.Domicilio beanDom=beanResponsable.getDomicilioResponsable();
        setDatosDomicilio(beanDom, domRespDB);
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ABMProveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.Domicilio beanDomicilioCliente;
    private metalsoft.beans.Responsable beanResponsable;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbCondicionIVA;
    private datechooser.beans.DateChooserCombo dccFechaAlta;
    private datechooser.beans.DateChooserCombo dccFechaBaja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCUIT;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtNroCliente;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

}
