/*
 * RegistrarRepresentanteProveedor.java
 *
 * Created on 5 de junio de 2008, 18:17
 */

package pkgCasosDeUso.pkgCompras.RegistrarRepresentanteProveedor;

import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgRecursosDeSoporte.Mensaje;
import pkgRecursosDeSoporte.Pantalla;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgClasesDeNegocio.Ventas.TipoTelefono;
import pkgRecursosDeSoporte.ValText;

/**
 *
 * @author  usuario
 */
public class PllaRegistrarRepresentanteProveedor extends javax.swing.JDialog {
    
    private GestorRegistrarRepresentanteProveedor gestor=new GestorRegistrarRepresentanteProveedor();
    
    private boolean datosCorrectos=true;
        
    /** Creates new form RegistrarRepresentanteProveedor */
    public PllaRegistrarRepresentanteProveedor() {
        initComponents();
    }

    public void opcionNuevoRepresentanteProveedor() {
         this.abrirVentana();
    }
    
   
    
   public void mostrarMensajePantalla(String mensaje,int tipoMensaje){
     Mensaje.mensaje_Estandar(mensaje,"Mensaje", tipoMensaje);
    }

    public void mostrarTelefonos(Lista telefonos) {
            
    Iterador iteradorTelefonos=telefonos.crearIterador();
                    
        while(iteradorTelefonos.siguiente()){
          
          TipoTelefono tipoTelefono=(TipoTelefono) iteradorTelefonos.getElementoActual();
          
          this.cmbTelefono1.addItem(tipoTelefono.getNombre());
          this.cmbTelefono2.addItem(tipoTelefono.getNombre());
          this.cmbTelefono3.addItem(tipoTelefono.getNombre());
          
        }           
     
       this.cmbTelefono2.setSelectedIndex(1);
       this.cmbTelefono3.setSelectedIndex(2);
       
    
    }

    public void mostrarMensajeRegistracionExitosa() {
        Mensaje.mensaje_Estandar("El Representante de Proveedor se registró Correctamente", "Representante de Proveedor cargado exitosamente", Mensaje.TIPO_INFORMACION);
    }

    public void mostrarProveedores(Lista proveedores) {

        Iterador iteradorProveedores=proveedores.crearIterador();
                    
        while(iteradorProveedores.siguiente()){
          
        Proveedor proveedor=(Proveedor) iteradorProveedores.getElementoActual();
          
        this.cmbProveedorAsociado.addItem(proveedor.getRazonSocial());
        }
        

    }

    private void abrirVentana() {
       
       Pantalla.centrarEnPantallaJDialog(this,9,30);
  
       this.habilitarCampos();
            
       this.getGestor().nuevoRepresentanteProveedor(this);
      
       this.setModal(true);
       this.setVisible(true);
    }
    
   public void mostrarProveedorSeleccionado(){
     if(this.gestor.getProveedorSeleccionado()!=null){
          this.cmbProveedorAsociado.addItem(this.gestor.getProveedorSeleccionado().getRazonSocial());
          this.cmbProveedorAsociado.setSelectedItem(this.gestor.getProveedorSeleccionado().getRazonSocial());
     }
        
        
    }
    
    public void deshabilitarCampos() {
    
      this.txtApellido.setEnabled(false); 
      this.txtNombre.setEnabled(false);
      this.txtCaracteristica1.setEnabled(false);
      this.txtCaracteristica2.setEnabled(false);
      this.txtCaracteristica3.setEnabled(false);
      this.txtTelefono1.setEnabled(false);
      this.txtTelefono2.setEnabled(false);
      this.txtTelefono3.setEnabled(false);
      this.cmbTelefono1.setEnabled(false);
      this.cmbTelefono2.setEnabled(false);
      this.cmbTelefono3.setEnabled(false);
      this.cmbProveedorAsociado.setEnabled(false);
    }

    private void habilitarCampos() 
    {
      this.txtApellido.setEnabled(true); 
      this.txtNombre.setEnabled(true);
      this.txtCaracteristica1.setEnabled(true);
      this.txtCaracteristica2.setEnabled(true);
      this.txtCaracteristica3.setEnabled(true);
      this.txtTelefono1.setEnabled(true);
      this.txtTelefono2.setEnabled(true);
      this.txtTelefono3.setEnabled(true);
      this.cmbTelefono1.setEnabled(true);
      this.cmbTelefono2.setEnabled(true);
      this.cmbTelefono3.setEnabled(true);
      this.cmbProveedorAsociado.setEnabled(true);
    }

    private void limpiarCampos() {
        
        this.txtApellido.setText("");
        this.txtNombre.setText("");
        this.txtCaracteristica1.setText("");
        this.txtCaracteristica2.setText("");
        this.txtCaracteristica3.setText("");
        this.txtTelefono1.setText("");
        this.txtTelefono2.setText("");
        this.txtTelefono3.setText("");
        
    }

    private void tomarConfirmacionNuevoProveedor() {
    int confirmacion = Mensaje.mensaje_Consulta_YES_NO_OPTION("¿Desea confirmar la registración?", "Confirmación de Representante de Proveedor", Mensaje.TIPO_INTERROGACION);
        getGestor().tomarConfirmacion(confirmacion);
    }
    
   private void tomarIngresoNombre(){
        if( this.txtNombre.getText()==null || this.txtNombre.getText().trim().length()==0 || ValText.existeNumeros(this.txtNombre.getText()))
        {
           Mensaje.mensaje_Estandar("Nombre Incorrecto", "Error en Nombre", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
   
        String nombre=this.txtNombre.getText();
        
        getGestor().tomarNombre(nombre);   
    }
    
      private void tomarIngresoApellido(){
        if( this.txtApellido.getText()==null || this.txtApellido.getText().trim().length()==0 || ValText.existeNumeros(this.txtApellido.getText()))
        {
           Mensaje.mensaje_Estandar("Apellido Incorrecto", "Error en Apellido", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
   
        String nombre=this.txtApellido.getText();
        
        getGestor().tomarApellido(nombre);   
    }
  
   private void tomarSeleccionProveedor(){

       String proveedor=(String)this.cmbProveedorAsociado.getSelectedItem();
       
        getGestor().tomarProveedor(proveedor);   
    }
          
   private void tomarIngresoTelefono1(){
       
       if( this.txtCaracteristica1.getText()==null || this.txtCaracteristica1.getText().trim().length()==0 || ValText.existeLetras(this.txtCaracteristica1.getText()))
        {
           Mensaje.mensaje_Estandar("Característica del Teléfono Principal Incorrecta o sin cargar", "Error en característica", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
              
       if( this.txtTelefono1.getText()==null || this.txtTelefono1.getText().trim().length()==0 || ValText.existeLetras(this.txtTelefono1.getText()))
        {
           Mensaje.mensaje_Estandar("Teléfono Principal Incorrecto o sin cargar", "Error en teléfono", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
   
       if( this.txtTelefono1.getText().trim().length()!=0 && this.txtCaracteristica1.getText().trim().length()==0)
        {
           Mensaje.mensaje_Estandar("Falta ingresar característica del Teléfono Principal", "Error en teléfono principal", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           this.txtCaracteristica1.requestFocus();
           return;
        }
       
       if( this.txtCaracteristica1.getText().trim().length()!=0 && this.txtTelefono1.getText().trim().length()==0)
        {
           Mensaje.mensaje_Estandar("Falta ingresar Teléfono Principal", "Error en teléfono principal", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           this.txtTelefono1.requestFocus();
           return;
        }
       
       int caracteristica=ValText.getEntero(this.txtCaracteristica1.getText());
       int numero=ValText.getEntero(this.txtTelefono1.getText());
       String tipoTelefono=(String)this.cmbTelefono1.getSelectedItem();
       
        getGestor().tomarNumero1(caracteristica, numero,tipoTelefono);   
    }
   
   private void tomarIngresoTelefono2(){
        if( this.txtCaracteristica2.getText().trim().length()!=0 && this.txtTelefono2.getText().trim().length()==0 )
        {
           Mensaje.mensaje_Estandar("Falta ingresar Teléfono", "Error en teléfono", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           this.txtTelefono2.requestFocus();
           return;
        }
       
       if (this.txtTelefono2.getText()==null || this.txtTelefono2.getText().trim().compareTo("")==0) 
           return;
       
       if(ValText.existeLetras(this.txtCaracteristica2.getText()))
        {
           Mensaje.mensaje_Estandar("Característica Incorrecta", "Error en característica", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
       
       if(ValText.existeLetras(this.txtTelefono2.getText()))
        {
           Mensaje.mensaje_Estandar("Teléfono Incorrecto", "Error en teléfono", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
       
       if( this.txtTelefono2.getText().trim().length()!=0 && this.txtCaracteristica2.getText().trim().length()==0)
        {
           Mensaje.mensaje_Estandar("Falta ingresar característica de Teléfono", "Error en teléfono", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           this.txtCaracteristica2.requestFocus();
           return;
        }
                 
       int caracteristica=ValText.getEntero(this.txtCaracteristica2.getText());
       int numero=ValText.getEntero(this.txtTelefono2.getText());
       String tipoTelefono=(String)this.cmbTelefono2.getSelectedItem();
       
        getGestor().tomarNumero2(caracteristica, numero,tipoTelefono);   
    }   
   
     private void tomarIngresoTelefono3(){
       if( this.txtCaracteristica3.getText().trim().length()!=0 && this.txtTelefono3.getText().trim().length()==0 )
        {
           Mensaje.mensaje_Estandar("Falta ingresar Teléfono", "Error en teléfono", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           this.txtTelefono3.requestFocus();
           return;
        }
         
       if (this.txtTelefono3.getText()==null || this.txtTelefono3.getText().trim().compareTo("")==0) 
       return;
        
       if(ValText.existeLetras(this.txtCaracteristica3.getText()))
        {
           Mensaje.mensaje_Estandar("Característica Incorrecta", "Error en característica", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
        
        if( ValText.existeLetras(this.txtTelefono3.getText()))
        {
           Mensaje.mensaje_Estandar("Teléfono Incorrecto", "Error en teléfono", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
        
        if( this.txtTelefono3.getText().trim().length()!=0 && this.txtCaracteristica3.getText().trim().length()==0)
        {
           Mensaje.mensaje_Estandar("Falta ingresar característica de Teléfono", "Error en teléfono", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           this.txtCaracteristica3.requestFocus();
           return;
        }
                           
       int caracteristica=ValText.getEntero(this.txtCaracteristica3.getText());
       int numero=ValText.getEntero(this.txtTelefono3.getText());
       String tipoTelefono=(String)this.cmbTelefono3.getSelectedItem();
       
        getGestor().tomarNumero3(caracteristica, numero,tipoTelefono);   
    } 
     

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTelefono = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtTelefono1 = new javax.swing.JTextField();
        txtTelefono2 = new javax.swing.JTextField();
        txtTelefono3 = new javax.swing.JTextField();
        cmbTelefono1 = new javax.swing.JComboBox();
        cmbTelefono2 = new javax.swing.JComboBox();
        cmbTelefono3 = new javax.swing.JComboBox();
        txtCaracteristica1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCaracteristica2 = new javax.swing.JTextField();
        txtCaracteristica3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbProveedorAsociado = new javax.swing.JComboBox();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGrabar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Representante de Proveedor");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Representante Proveedor"));

        lblTelefono.setText("Teléfonos:");

        lblApellido.setText("Apellido:");

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoKeyReleased(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        lblNombre.setText("Nombre:");

        txtTelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefono1KeyReleased(evt);
            }
        });

        txtTelefono2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefono2KeyReleased(evt);
            }
        });

        txtTelefono3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefono3KeyReleased(evt);
            }
        });

        cmbTelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTelefono1KeyReleased(evt);
            }
        });

        cmbTelefono2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTelefono2KeyReleased(evt);
            }
        });

        cmbTelefono3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTelefono3KeyReleased(evt);
            }
        });

        txtCaracteristica1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCaracteristica1KeyReleased(evt);
            }
        });

        jLabel2.setText("-"); // NOI18N

        txtCaracteristica2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCaracteristica2KeyReleased(evt);
            }
        });

        txtCaracteristica3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCaracteristica3KeyReleased(evt);
            }
        });

        jLabel3.setText("-"); // NOI18N

        jLabel4.setText("-"); // NOI18N

        jLabel1.setText("Proveedor Asociado:");

        cmbProveedorAsociado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbProveedorAsociadoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addComponent(cmbProveedorAsociado, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(201, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblApellido)
                    .addComponent(lblTelefono)
                    .addComponent(lblNombre))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtApellido)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCaracteristica3, 0, 0, Short.MAX_VALUE)
                            .addComponent(txtCaracteristica2, 0, 0, Short.MAX_VALUE)
                            .addComponent(txtCaracteristica1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTelefono3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTelefono2)
                            .addComponent(txtTelefono1, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTelefono2, 0, 99, Short.MAX_VALUE)
                    .addComponent(cmbTelefono3, 0, 99, Short.MAX_VALUE)
                    .addComponent(cmbTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefono)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCaracteristica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCaracteristica2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTelefono3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCaracteristica3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbProveedorAsociado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });
        btnGrabar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGrabarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnGrabarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGrabar)
                    .addComponent(btnNuevo))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    
    int confirmacionCancelar = Mensaje.mensaje_Consulta_YES_NO_OPTION("¿Desea cancelar?", "Confirmación de cancelación", Mensaje.TIPO_INTERROGACION);
    if (confirmacionCancelar==0)
    {
       this.gestor.finCU(); 
    }        
    }//GEN-LAST:event_btnCancelarActionPerformed

    public void cerrarPantalla(){
     this.dispose();
    }
    
    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
 datosCorrectos=true;

        this.tomarIngresoNombre();        

        if (this.datosCorrectos==true){
        this.tomarIngresoApellido();
        
        if (this.datosCorrectos==true){
        this.tomarIngresoTelefono1();
        
        if (this.datosCorrectos==true){
        this.tomarIngresoTelefono2();
        
        if (this.datosCorrectos==true){
        this.tomarIngresoTelefono3();
        
        if (this.datosCorrectos==true){
        this.tomarSeleccionProveedor();
        
        
        if (this.datosCorrectos==true){
      
            try {
                  this.tomarConfirmacionNuevoProveedor();
                 } 
           catch (Exception ex) 
           {
             Mensaje.mensaje_Estandar("No se pudo grabar el Representante", "Error en grabación", Mensaje.TIPO_ERROR);
           }
        
        }
        }    
        }
        }
        }
        }
        
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.limpiarCampos();
        this.habilitarCampos();
        getGestor().prepararParaIniciarDeNuevo();
        this.txtNombre.requestFocus();    
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
      if (evt.getKeyCode()==10)
      {
          this.txtApellido.requestFocus();
      }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyReleased
      if (evt.getKeyCode()==10)
      {
          this.txtCaracteristica1.requestFocus();
      }
    }//GEN-LAST:event_txtApellidoKeyReleased

    private void txtCaracteristica1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCaracteristica1KeyReleased
     if (evt.getKeyCode()==10)
      {
          this.txtTelefono1.requestFocus();
      }
    }//GEN-LAST:event_txtCaracteristica1KeyReleased

    private void txtTelefono1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono1KeyReleased
      if (evt.getKeyCode()==10)
      {
          this.cmbTelefono1.requestFocus();
      }
    }//GEN-LAST:event_txtTelefono1KeyReleased

    private void cmbTelefono1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTelefono1KeyReleased
      if (evt.getKeyCode()==10)
      {
          this.txtCaracteristica2.requestFocus();
      }
    }//GEN-LAST:event_cmbTelefono1KeyReleased

    private void txtCaracteristica2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCaracteristica2KeyReleased
      if (evt.getKeyCode()==10)
      {
          this.txtTelefono2.requestFocus();
      }
    }//GEN-LAST:event_txtCaracteristica2KeyReleased

    private void txtTelefono2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono2KeyReleased
      if (evt.getKeyCode()==10)
      {
          this.cmbTelefono2.requestFocus();
      }
    }//GEN-LAST:event_txtTelefono2KeyReleased

    private void cmbTelefono2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTelefono2KeyReleased
      if (evt.getKeyCode()==10)
      {
          this.txtCaracteristica3.requestFocus();
      }
    }//GEN-LAST:event_cmbTelefono2KeyReleased

    private void txtCaracteristica3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCaracteristica3KeyReleased
      if (evt.getKeyCode()==10)
      {
          this.txtTelefono3.requestFocus();
      }
    }//GEN-LAST:event_txtCaracteristica3KeyReleased

    private void txtTelefono3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono3KeyReleased
      if (evt.getKeyCode()==10)
      {
          this.cmbTelefono3.requestFocus();
      }
    }//GEN-LAST:event_txtTelefono3KeyReleased

    private void cmbTelefono3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTelefono3KeyReleased
      if (evt.getKeyCode()==10)
      {
          this.cmbProveedorAsociado.requestFocus();
      }
    }//GEN-LAST:event_cmbTelefono3KeyReleased

    private void cmbProveedorAsociadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbProveedorAsociadoKeyReleased
      if (evt.getKeyCode()==10)
      {
         this.btnGrabar.requestFocus();
      }
    }//GEN-LAST:event_cmbProveedorAsociadoKeyReleased

    private void btnGrabarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGrabarKeyReleased
    }//GEN-LAST:event_btnGrabarKeyReleased

    private void btnGrabarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGrabarKeyPressed
    if (evt.getKeyCode()==10)
    {
        datosCorrectos=true;

        this.tomarIngresoNombre();        

        if (this.datosCorrectos==true){
        this.tomarIngresoApellido();
        
        if (this.datosCorrectos==true){
        this.tomarIngresoTelefono1();
        
        if (this.datosCorrectos==true){
        this.tomarIngresoTelefono2();
        
        if (this.datosCorrectos==true){
        this.tomarIngresoTelefono3();
        
        if (this.datosCorrectos==true){
        this.tomarSeleccionProveedor();
        
        
        if (this.datosCorrectos==true){
      
            try {
                  this.tomarConfirmacionNuevoProveedor();
                 } 
           catch (Exception ex) 
           {
             Mensaje.mensaje_Estandar("No se pudo grabar el Representante", "Error en grabación", Mensaje.TIPO_ERROR);
           }
        
        }
        }    
        }
        }
        }
        }
    }
        

    }//GEN-LAST:event_btnGrabarKeyPressed
    
  
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cmbProveedorAsociado;
    private javax.swing.JComboBox cmbTelefono1;
    private javax.swing.JComboBox cmbTelefono2;
    private javax.swing.JComboBox cmbTelefono3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCaracteristica1;
    private javax.swing.JTextField txtCaracteristica2;
    private javax.swing.JTextField txtCaracteristica3;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTelefono2;
    private javax.swing.JTextField txtTelefono3;
    // End of variables declaration//GEN-END:variables

    public GestorRegistrarRepresentanteProveedor getGestor() {
        return gestor;
    }

    public void setGestor(GestorRegistrarRepresentanteProveedor gestor) {
        this.gestor = gestor;
    }
    
}
