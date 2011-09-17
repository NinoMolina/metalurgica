/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ABMResponable.java
 *
 * Created on 30/06/2010, 19:00:29
 */

package metalsoft.presentacion;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.compras.Responsable;
import metalsoft.negocio.gestores.GestorCliente;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.rrhh.Domicilio;
import metalsoft.negocio.rrhh.TipoDocumento;
import metalsoft.negocio.ventas.CondicionIva;

/**
 *
 * @author Nino
 */
public class ABMResponsable extends javax.swing.JFrame implements IDomiciliable{
    private GestorCliente gestor;
    private Responsable responsable;
    private metalsoft.datos.dbobject.ResponsableDB responsableDB;
    private IResponsable ventana;
    private Domicilio domicilio;
    private long idDomicilio;
    private long idResponsable;
    private boolean devolverObjeto=false;
    /** Creates new form ABMResponable */
    public ABMResponsable() {
        initComponents();
        addListenerBtnGuardar();
        addListenerBtnSalir();
    }

    private void addListenerBtnGuardar() {
        botones.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        String apellido=txtApellido.getText();
        String email=txtEmail.getText();
        String fax=txtFax.getText();
        String nombre=txtNombre.getText();
        int nroDoc=Integer.parseInt(txtNroDoc.getText());
        String telefono=txtTelefono.getText();
        ItemCombo item=(ItemCombo)cmbTipoDoc.getSelectedItem();
        int indexTipoDoc=Integer.parseInt(item.getId());
        metalsoft.negocio.rrhh.TipoDocumento tipoDoc=new TipoDocumento();
        tipoDoc.setTipo(item.getMostrar());

        responsable=new Responsable();
        responsable.setFax(fax);
        responsable.setApellido(apellido);
        responsable.setDomicilio(domicilio);
        responsable.setEmail(email);
        responsable.setNombre(nombre);
        responsable.setNroDocumento(nroDoc);
        responsable.setTelefono(telefono);
        responsable.setTipoDocumento(tipoDoc);

        responsableDB=new metalsoft.datos.dbobject.ResponsableDB();
        if(devolverObjeto)
        {
            responsableDB=Parser.parseToResponsableDB(responsable);
            responsableDB.setTipodocumento(gestor.obtenerIdTipoDoc(indexTipoDoc));
            ventana.setResponsable(responsable, responsableDB);
            ventana.mostrarDatosResponsable();
            this.dispose();
        }
        else
        {
            idResponsable=gestor.registrarResponsable(responsable,indexTipoDoc,idDomicilio);
            if(idResponsable>0)JOptionPane.showMessageDialog(this, "El responsable se guardó correctamente");
            else JOptionPane.showMessageDialog(this, "No se pudo guardar el responsable");
        }        
    }

    public boolean isDevolverObjeto() {
        return devolverObjeto;
    }

    public void setDevolverObjeto(boolean devolverObjeto) {
        this.devolverObjeto = devolverObjeto;
    }


    public IResponsable getVentana() {
        return ventana;
    }

    public void setVentana(IResponsable ventana) {
        this.ventana = ventana;
    }

    public GestorCliente getGestor() {
        return gestor;
    }

    public void setGestor(GestorCliente gestor) {
        this.gestor = gestor;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public void setDomicilio(Domicilio dom, metalsoft.datos.dbobject.DomicilioDB domDB) {
        domicilio=dom;
        gestor.tomarDomicilioResponsable(domDB);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botones = new metalsoft.beans.ABM_Botones();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbTipoDoc = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        btnRegistrarDomicilio = new javax.swing.JButton();
        lblDomicilio = new javax.swing.JLabel();
        txtNroDoc = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtFax = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombre:");

        jLabel2.setText("Apellido:");

        jLabel3.setText("Email:");

        jLabel4.setText("Fax:");

        jLabel5.setText("Nro. Documento:");

        jLabel6.setText("Teléfono:");

        jLabel7.setText("Tipo documento:");

        cmbTipoDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoDocActionPerformed(evt);
            }
        });

        jLabel8.setText("Domicilio:");

        btnRegistrarDomicilio.setText("Registrar");
        btnRegistrarDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarDomicilioActionPerformed(evt);
            }
        });

        lblDomicilio.setText(":");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRegistrarDomicilio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtApellido, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cmbTipoDoc, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtFax))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(botones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(txtNroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarDomicilio)
                    .addComponent(jLabel8)
                    .addComponent(lblDomicilio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoDocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoDocActionPerformed

    private void btnRegistrarDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarDomicilioActionPerformed
        RegistrarDomicilio frmRegDomicilio=null;
        try {
            frmRegDomicilio=(RegistrarDomicilio) JFrameManager.crearVentana(RegistrarDomicilio.class.getName());
            frmRegDomicilio.setGestor(gestor);
            frmRegDomicilio.setDevolverObjeto(true);
            frmRegDomicilio.setVentana(this);
            frmRegDomicilio.cargarComboProvincia();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegistrarDomicilioActionPerformed

    public void cargarComboTipoDoc() {
        gestor.obtenerTipoDocumentos(cmbTipoDoc);
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ABMResponsable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JButton btnRegistrarDomicilio;
    private javax.swing.JComboBox cmbTipoDoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDomicilio;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNroDoc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    public void setDomicilio(Domicilio dom, long id) {
        domicilio=dom;
        this.idDomicilio=id;
    }

    public void mostrarDatosDomicilio() {
        String prov=domicilio.getBarrio().getLocalidad().getProvincia().getNombre();
        String barrio=domicilio.getBarrio().getNombre();
        String localidad=domicilio.getBarrio().getLocalidad().getNombre();
        String calle=domicilio.getCalle();
        String numero=String.valueOf(domicilio.getNumeroCalle());
        lblDomicilio.setText(prov+","+localidad+","+barrio+","+calle+","+numero);
    }


}
