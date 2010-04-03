/*
 * PllaRegistrarProveedor.java
 *
 * Created on 8 de junio de 2008, 21:15
 */

package pkgCasosDeUso.pkgCompras.RegistrarProveedor;

import java.util.logging.Level;
import java.util.logging.Logger;
import pkgCasosDeUso.pkgCompras.RegistrarProveedor.PllaAsignacionProductosProveedor;
import pkgClasesDeNegocio.AdministracionDePersonal.Ciudad;
import pkgClasesDeNegocio.AdministracionDePersonal.Pais;
import pkgClasesDeNegocio.AdministracionDePersonal.Provincia;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgClasesDeNegocio.Ventas.CondicionIva;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgClasesDeNegocio.Ventas.TipoTelefono;
import pkgRecursosDeSoporte.Mensaje;
import pkgRecursosDeSoporte.Pantalla;
import pkgRecursosDeSoporte.ValText;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author  Armando
 */
public class PllaRegistrarProveedor extends javax.swing.JDialog {
    
    private GestorRegistrarProveedor gestor=new GestorRegistrarProveedor();
    
    private boolean datosCorrectos=true;
    
    private int bandDomicilio=0;
    
    /** Creates new form PllaRegistrarProveedor */
    public PllaRegistrarProveedor() {
        initComponents();
        
    }
    
    public void opcionNuevoProveedor(){
        this.abrirVentana();
    }

    public void mostrarCondicionIva(Lista condicionesIva) {
        Iterador iteradorCondicionIva=condicionesIva.crearIterador();
        
        this.cmbCondicionIVA.removeAllItems();
        
        while(iteradorCondicionIva.siguiente()){
          CondicionIva condicionIva=(CondicionIva) iteradorCondicionIva.getElementoActual();
          
          this.cmbCondicionIVA.addItem(condicionIva.getNombre());
         
        }
    }

     public void mostrarProvincia(Lista provincias) {
        Iterador iteradorProvincia=provincias.crearIterador();
        
        this.cmbProvinciaDomicilio.removeAllItems();
        while(iteradorProvincia.siguiente()){
          Provincia provincia=(Provincia) iteradorProvincia.getElementoActual();
          
          this.cmbProvinciaDomicilio.addItem(provincia.getNombre());
         
        }
       
    }
     
     public void mostrarCiudad(Lista ciudades) {
        Iterador iteradorCiudad=ciudades.crearIterador();
        
       this.cmbCiudadDomicilio.removeAllItems();
             
        while(iteradorCiudad.siguiente()){
          Ciudad ciudad=(Ciudad) iteradorCiudad.getElementoActual();
          
          this.cmbCiudadDomicilio.addItem(ciudad.getNombre());
         
        }
       
    }
     
  public void mostrarPaisesDomicilio(Lista paises) {
        
        Iterador itPaises=paises.crearIterador();
        
         while(itPaises.siguiente()){
         Pais p=(Pais) itPaises.getElementoActual();
         
         this.cmbPaisDomicilio.addItem(p.getNombre());
          
        }
   
    } 
  
   public void mostrarProvinciaDomicilio(Lista provincia) {
        
        Iterador itProvincia=provincia.crearIterador();

        this.bandDomicilio=1;
        this.cmbProvinciaDomicilio.removeAllItems();
        
        this.cmbProvinciaDomicilio.addItem("Seleccione Provincia..."); 
        
        while(itProvincia.siguiente()){
            
            Provincia p=(Provincia) itProvincia.getElementoActual();
            this.cmbProvinciaDomicilio.addItem(p.getNombre());
    
        }
         this.bandDomicilio=0;
        
        this.mostrarCiudadesDomicilio(new Lista());
      
 
    }
     
   public void mostrarCiudadesDomicilio(Lista ciudad) {
       
       Iterador itCiudad=ciudad.crearIterador();  
        
       this.cmbCiudadDomicilio.removeAllItems(); 
       this.cmbCiudadDomicilio.addItem("Seleccione Ciudad...");
       
        while(itCiudad.siguiente()){
            
            Ciudad c=(Ciudad) itCiudad.getElementoActual();
            this.cmbCiudadDomicilio.addItem(c.getNombre());
    
        }

    }
   
   public void mostrarProductoSiTipoProveedorSeleccionado(){
    PllaAsignacionProductosProveedor plla=new PllaAsignacionProductosProveedor();
    plla.abrirVentana(gestor);
   
    
   
   }
   
    public void mostrarTipoProveedor(Lista tiposProveedor) {
       Iterador iteradorTipoProveedor=tiposProveedor.crearIterador();
       int fila=0; 
                    
        while(iteradorTipoProveedor.siguiente()){
          TipoProveedor tipoProveedor=(TipoProveedor) iteradorTipoProveedor.getElementoActual();
          
          this.tblTipoProveedor.setValueAt(tipoProveedor.getNombre(),fila,0);
          
          fila++;
        }
       
       this.tblTipoProveedor.setShowGrid(true);
    
    }

    public void mostrarMensajeRegistracionExitosa() {
        Mensaje.mensaje_Estandar("El Proveedor se registró Correctamente", "Proveedor cargado exitosamente", Mensaje.TIPO_INFORMACION);
        
        this.dispose();
    }

    public void mostrarTelefonos(Lista tipoTelefono) {
          
    Iterador iteradorTipoTelefonos=tipoTelefono.crearIterador();
                    
        while(iteradorTipoTelefonos.siguiente()){
          
          TipoTelefono tipoTelefonos=(TipoTelefono) iteradorTipoTelefonos.getElementoActual();
 
          this.cmbTipoTelefono1.addItem(tipoTelefonos.getNombre());
          this.cmbTipoTelefono2.addItem(tipoTelefonos.getNombre());
          this.cmbTipoTelefono3.addItem(tipoTelefonos.getNombre());
          
        }           
     
       this.cmbTipoTelefono2.setSelectedIndex(1);
       this.cmbTipoTelefono3.setSelectedIndex(2);
       
    }
  
    private void abrirVentana(){
       Pantalla.centrarEnPantallaJDialog(this,9,30);
       
       this.deshabilitar();
       this.gestor.nuevoProveedor(this);
       
       this.setModal(true);
       this.setVisible(true);
    }
    
    public void mostrarMensajePantalla(String mensaje,int tipoMensaje){
     Mensaje.mensaje_Estandar(mensaje,"Mensaje", tipoMensaje);
    }

    private void deshabilitar() 
    {
       this.tblTipoProveedor.setEnabled(false);
       this.cmbCondicionIVA.setEnabled(false);
       this.cmbPaisDomicilio.setEnabled(false);
       this.cmbProvinciaDomicilio.setEnabled(false);
       this.cmbCiudadDomicilio.setEnabled(false);
       this.txtRazonSocial.setEnabled(false);
       this.txtNombreCalle.setEnabled(false);
       this.txtNombreBarrio.setEnabled(false);
       this.txtNroCalle.setEnabled(false);
       this.chbDepartamento.setEnabled(false);
       this.txtTelefono1.setEnabled(false);
       this.txtTelefono2.setEnabled(false);
       this.txtTelefono3.setEnabled(false);
       this.txtCaracteristica1.setEnabled(false);
       this.txtCaracteristica2.setEnabled(false);
       this.txtCaracteristica3.setEnabled(false);
       this.cmbTipoTelefono1.setEnabled(false);
       this.cmbTipoTelefono2.setEnabled(false);
       this.cmbTipoTelefono3.setEnabled(false);
       this.txtNroIngresoBruto.setEnabled(false);
       this.btnAsignarProductos.setEnabled(false);
       this.btnRegistrarProveedor.setEnabled(false);
       this.btnRegistrarRepresentante.setEnabled(false);
    }
  
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTipoProveedor = new javax.swing.JTable();
        lblTipoProveedor = new javax.swing.JLabel();
        lblRazonSocial = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtNumeroCuit = new javax.swing.JTextField();
        lblNumeroCuit = new javax.swing.JLabel();
        btnComprobarNumero = new javax.swing.JButton();
        jPDatosDomicilio = new javax.swing.JPanel();
        lblNombreCalle = new javax.swing.JLabel();
        txtNombreCalle = new javax.swing.JTextField();
        lblNroCalle = new javax.swing.JLabel();
        txtNroCalle = new javax.swing.JTextField();
        chbDepartamento = new javax.swing.JCheckBox();
        lblDepartamento = new javax.swing.JLabel();
        txtDepartamento = new javax.swing.JTextField();
        lblNroPiso = new javax.swing.JLabel();
        lblPais = new javax.swing.JLabel();
        cmbPaisDomicilio = new javax.swing.JComboBox();
        lblProvincia = new javax.swing.JLabel();
        cmbProvinciaDomicilio = new javax.swing.JComboBox();
        lblCiudad = new javax.swing.JLabel();
        cmbCiudadDomicilio = new javax.swing.JComboBox();
        lblBarrio = new javax.swing.JLabel();
        txtNombreBarrio = new javax.swing.JTextField();
        spDatosDepartamento = new javax.swing.JSeparator();
        spDatosPais = new javax.swing.JSeparator();
        txtNumeroPiso = new javax.swing.JTextField();
        btnRegistrarRepresentante = new javax.swing.JButton();
        txtTelefono1 = new javax.swing.JTextField();
        btnAsignarProductos = new javax.swing.JButton();
        txtCaracteristica2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblNroIngresoBruto = new javax.swing.JLabel();
        lblTelefonos = new javax.swing.JLabel();
        cmbCondicionIVA = new javax.swing.JComboBox();
        txtNroIngresoBruto = new javax.swing.JTextField();
        txtCaracteristica3 = new javax.swing.JTextField();
        lblTelefono3 = new javax.swing.JLabel();
        txtTelefono3 = new javax.swing.JTextField();
        txtTelefono2 = new javax.swing.JTextField();
        txtCaracteristica1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbTipoTelefono1 = new javax.swing.JComboBox();
        cmbTipoTelefono2 = new javax.swing.JComboBox();
        cmbTipoTelefono3 = new javax.swing.JComboBox();
        btnRegistrarProveedor = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Proveedor"); // NOI18N
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Proveedor"));

        tblTipoProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tipo Proveedor", "Asignar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTipoProveedor);

        lblTipoProveedor.setText("Tipo de Proveedor:"); // NOI18N

        lblRazonSocial.setText("Razón Social:"); // NOI18N

        lblNumeroCuit.setText("Número de CUIT:"); // NOI18N

        btnComprobarNumero.setText("Verificar Número"); // NOI18N
        btnComprobarNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprobarNumeroActionPerformed(evt);
            }
        });

        jPDatosDomicilio.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio"));

        lblNombreCalle.setText("Nombre de Calle:"); // NOI18N

        txtNombreCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCalleActionPerformed(evt);
            }
        });

        lblNroCalle.setText("Número de Calle:"); // NOI18N

        chbDepartamento.setText("Datos Departamento"); // NOI18N
        chbDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbDepartamentoActionPerformed(evt);
            }
        });

        lblDepartamento.setText("Departamento:"); // NOI18N
        lblDepartamento.setEnabled(false);

        txtDepartamento.setEnabled(false);
        txtDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartamentoActionPerformed(evt);
            }
        });

        lblNroPiso.setText("Número de Piso:"); // NOI18N
        lblNroPiso.setEnabled(false);

        lblPais.setText("País:"); // NOI18N

        cmbPaisDomicilio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Pais..." }));
        cmbPaisDomicilio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbPaisDomicilioMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbPaisDomicilioMouseReleased(evt);
            }
        });
        cmbPaisDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPaisDomicilioActionPerformed(evt);
            }
        });

        lblProvincia.setText("Provincia:"); // NOI18N

        cmbProvinciaDomicilio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Provincia..." }));
        cmbProvinciaDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvinciaDomicilioActionPerformed(evt);
            }
        });

        lblCiudad.setText("Ciudad:"); // NOI18N

        cmbCiudadDomicilio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Ciudad..." }));
        cmbCiudadDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCiudadDomicilioActionPerformed(evt);
            }
        });

        lblBarrio.setText("Nombre de Barrio:"); // NOI18N

        javax.swing.GroupLayout jPDatosDomicilioLayout = new javax.swing.GroupLayout(jPDatosDomicilio);
        jPDatosDomicilio.setLayout(jPDatosDomicilioLayout);
        jPDatosDomicilioLayout.setHorizontalGroup(
            jPDatosDomicilioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPDatosDomicilioLayout.createSequentialGroup()
                .addGroup(jPDatosDomicilioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblBarrio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombreCalle, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDatosDomicilioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNombreBarrio)
                    .addComponent(txtNombreCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(lblNroCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNroCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(jPDatosDomicilioLayout.createSequentialGroup()
                .addGroup(jPDatosDomicilioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPDatosDomicilioLayout.createSequentialGroup()
                        .addComponent(lblDepartamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDepartamento))
                    .addComponent(chbDepartamento, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addComponent(lblNroPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(263, 263, 263))
            .addGroup(jPDatosDomicilioLayout.createSequentialGroup()
                .addComponent(lblPais, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPaisDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(lblProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbProvinciaDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lblCiudad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCiudadDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(spDatosDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
            .addComponent(spDatosPais, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
        );
        jPDatosDomicilioLayout.setVerticalGroup(
            jPDatosDomicilioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDatosDomicilioLayout.createSequentialGroup()
                .addGroup(jPDatosDomicilioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPDatosDomicilioLayout.createSequentialGroup()
                        .addGroup(jPDatosDomicilioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreCalle)
                            .addComponent(txtNroCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNroCalle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBarrio))
                    .addGroup(jPDatosDomicilioLayout.createSequentialGroup()
                        .addComponent(txtNombreCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spDatosDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chbDepartamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDatosDomicilioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDepartamento)
                    .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNroPiso)
                    .addComponent(txtNumeroPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(spDatosPais, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPDatosDomicilioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPais)
                    .addComponent(cmbPaisDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCiudad)
                    .addComponent(cmbCiudadDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProvinciaDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProvincia))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnRegistrarRepresentante.setText("Registrar Representantes"); // NOI18N

        btnAsignarProductos.setText("Asignar Productos"); // NOI18N
        btnAsignarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarProductosActionPerformed(evt);
            }
        });

        jLabel1.setText("Condición IVA:"); // NOI18N

        lblNroIngresoBruto.setText("Número Ingreso Bruto:"); // NOI18N

        lblTelefonos.setText("Teléfonos:"); // NOI18N

        txtTelefono2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefono2ActionPerformed(evt);
            }
        });

        jLabel2.setText("-"); // NOI18N

        jLabel3.setText("-"); // NOI18N

        jLabel4.setText("-"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTelefonos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCaracteristica1, 0, 0, Short.MAX_VALUE)
                                    .addComponent(txtCaracteristica3)
                                    .addComponent(txtCaracteristica2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTelefono3)
                                    .addComponent(txtTelefono2)
                                    .addComponent(txtTelefono1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbTipoTelefono3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbTipoTelefono2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbTipoTelefono1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                        .addGap(27, 27, 27))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblNroIngresoBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNroIngresoBruto)
                                    .addComponent(cmbCondicionIVA, 0, 152, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTipoProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblNumeroCuit, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(lblRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtRazonSocial, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNumeroCuit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnComprobarNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE))))
                            .addComponent(jPDatosDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblTelefono3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnRegistrarRepresentante)
                        .addGap(28, 28, 28)
                        .addComponent(btnAsignarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblNumeroCuit)
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRazonSocial)
                            .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNumeroCuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnComprobarNumero)))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoProveedor)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jPDatosDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTelefono3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCaracteristica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTelefonos)
                                    .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCaracteristica2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCaracteristica3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTelefono3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbTipoTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbTipoTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNroIngresoBruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNroIngresoBruto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbCondicionIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbTipoTelefono3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarRepresentante)
                    .addComponent(btnAsignarProductos))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnRegistrarProveedor.setText("Registrar Proveedor"); // NOI18N
        btnRegistrarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnRegistrarProveedorMouseReleased(evt);
            }
        });
        btnRegistrarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarProveedorActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar"); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(btnRegistrarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarProveedor)
                    .addComponent(btnCancelar))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAsignarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarProductosActionPerformed
     //PllaAsignacionProductosProveedor pAsigPro=new PllaAsignacionProductosProveedor();
     //Pantalla.centrarEnPantallaJFrame(pAsigPro);
     //pAsigPro.setVisible(true);
   this.tblTipoProveedor.setShowGrid(true);     
        
   for(int i=0;i<tblTipoProveedor.getRowCount() && tblTipoProveedor.getValueAt(i,0)!=null;i++){
       
     
        Boolean seleccion=(Boolean)tblTipoProveedor.getValueAt(i,1);
        if(seleccion!=null){
         String nomTipoProveedor=(String) tblTipoProveedor.getValueAt(i,0);
            
         gestor.buscarProductosSegunTipoProveedorSeleccionado(nomTipoProveedor);
        
        
        }
   }  
        
          this.mostrarProductoSiTipoProveedorSeleccionado();      
        

    }//GEN-LAST:event_btnAsignarProductosActionPerformed

    public void actualizarTabla(){
     
         for(int i=0;i<tblTipoProveedor.getRowCount() && tblTipoProveedor.getValueAt(i,0)!=null;i++){
    
         tblTipoProveedor.setValueAt(null,i,1);
         tblTipoProveedor.setValueAt(null,i,0);
   
        }
         
        this.mostrarTipoProveedor(this.gestor.getTipoProveedor()); 
        
    }
    
    
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
      this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNombreCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCalleActionPerformed

    private void txtDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartamentoActionPerformed

    private void chbDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbDepartamentoActionPerformed
        if(this.chbDepartamento.isSelected()){
         this.lblDepartamento.setEnabled(true);
         this.txtDepartamento.setEnabled(true);
         this.lblNroPiso.setEnabled(true);
         this.txtNumeroPiso.setEnabled(true);
        }
        else{
         this.lblDepartamento.setEnabled(false);
         this.txtDepartamento.setEnabled(false);
         this.lblNroPiso.setEnabled(false);
         this.txtNumeroPiso.setEnabled(false);
        }
    }//GEN-LAST:event_chbDepartamentoActionPerformed

    private void tomarIngresoNroCUIT(){
        if ( this.txtNumeroCuit.getText()==null || this.txtNumeroCuit.getText().trim().length()==0 || ValText.existeLetras(this.txtNumeroCuit.getText())){
               Mensaje.mensaje_Estandar("Número de CUIT Incorrecto", "Error en Número de CUIT", Mensaje.TIPO_ERROR);
               datosCorrectos=false;
               return;
            }
   
        int nroCuit=ValText.getEntero(this.txtNumeroCuit.getText());
        
        gestor.tomarNroCUIT(nroCuit);   
    }
    
    private void tomarIngresoNroIngresoBruto(){
        int nroIngresoBruto;
                
        if( ValText.existeLetras(this.txtNroIngresoBruto.getText()) )
        {
           Mensaje.mensaje_Estandar("Número de Ingreso Bruto Incorrecta", "Error en Número de Ingreso Bruto", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
        
        if (this.txtNroIngresoBruto.getText()==null||this.txtNroIngresoBruto.getText().trim().length()==0) {
                nroIngresoBruto=-1;
        }
        else {
             nroIngresoBruto=ValText.getEntero(this.txtNroIngresoBruto.getText());
        }
                
        gestor.tomarNroIngresoBruto(nroIngresoBruto);   
    }
    
    private void tomarIngresoRazonSocial(){
        if( this.txtRazonSocial.getText()==null || this.txtRazonSocial.getText().trim().length()==0)
        {
           Mensaje.mensaje_Estandar("Razon Social Incorrecta", "Error en Razon Social", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
   
        String razonSocial=this.txtRazonSocial.getText();
        
        gestor.tomarRazonSocial(razonSocial);   
    }
    
   private void tomarIngresoTelefonos(){

       if( ValText.existeLetras(this.txtTelefono1.getText()) || ValText.existeLetras(this.txtCaracteristica1.getText()) )
        {
           Mensaje.mensaje_Estandar("Número de Teléfono Principal Incorrecto", "Error en Teléfono", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
        
        if( ValText.existeLetras(this.txtCaracteristica2.getText())|| ValText.existeLetras(this.txtCaracteristica2.getText()) )
        {
           Mensaje.mensaje_Estandar("Número de Teléfono Secundario Incorrecto", "Error en Teléfono", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
       
        if( ValText.existeLetras(this.txtCaracteristica3.getText())|| ValText.existeLetras(this.txtCaracteristica3.getText()) )
        {
           Mensaje.mensaje_Estandar("Tercer número de Teléfono Incorrecto", "Error en Teléfono", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
        
       Lista telefonos=new Lista();
        if(this.txtTelefono1.getText()!=null && this.txtTelefono1.getText().trim().length()!=0 && this.txtCaracteristica1.getText()!=null && this.txtCaracteristica1.getText().trim().length()!=0)
        {
            Telefono telefono1 = new Telefono();
            telefono1.setNumero(ValText.getEntero(this.txtTelefono1.getText()));
            telefono1.setCaracteristica(ValText.getEntero(this.txtCaracteristica1.getText()));
            telefonos.insertarUltimo(telefono1);
        }
       
        if(this.txtTelefono2.getText()!=null && this.txtTelefono2.getText().trim().length()!=0 && this.txtCaracteristica2.getText()!=null && this.txtCaracteristica2.getText().trim().length()!=0)
        {
            Telefono telefono2 = new Telefono();
            telefono2.setNumero(ValText.getEntero(this.txtTelefono2.getText()));
            telefono2.setCaracteristica(ValText.getEntero(this.txtCaracteristica2.getText()));
            telefonos.insertarUltimo(telefono2);
        }
       
       if(this.txtTelefono3.getText()!=null && this.txtTelefono3.getText().trim().length()!=0 && this.txtCaracteristica3.getText()!=null && this.txtCaracteristica3.getText().trim().length()!=0)
        {
            Telefono telefono3 = new Telefono();
            telefono3.setNumero(ValText.getEntero(this.txtTelefono3.getText()));
            telefono3.setCaracteristica(ValText.getEntero(this.txtCaracteristica3.getText()));
            telefonos.insertarUltimo(telefono3);
        }
       
        gestor.tomarTelefonos(telefonos);     
    }
    
    private void tomarIngresoDomicilio(){
        int numeroPiso;
        int numeroCalle;
        String departamento;
        
        if( ValText.existeLetras(this.txtNroCalle.getText()))
        {
           Mensaje.mensaje_Estandar("Número de calle Incorrecto", "Error en Calle", Mensaje.TIPO_ERROR);
           this.datosCorrectos=false;
           datosCorrectos=false;
           return;
        }
        
        if( ValText.existeLetras(this.txtNumeroPiso.getText())==true)
        {
           Mensaje.mensaje_Estandar("Número de piso Incorrecto", "Error en Piso", Mensaje.TIPO_ERROR);
           datosCorrectos=false;
           return;
        }
     
        if (this.txtNroCalle.getText()==null||this.txtNroCalle.getText().trim().length()==0) {
                numeroCalle=-1;
        }
        else {
             numeroCalle=ValText.getEntero(this.txtNroCalle.getText());
        }
        
        if(this.chbDepartamento.isSelected()){
        if (this.txtNumeroPiso.getText()==null||this.txtNumeroPiso.getText().trim().length()==0) {
                numeroPiso=-1;
        }
        else {
             numeroPiso=ValText.getEntero(this.txtNumeroPiso.getText());
        }
        }
        
        else{
            numeroPiso=0;
        }
        
        if (this.chbDepartamento.isSelected()){
            departamento = this.txtDepartamento.getText();  
        }
        else
        {
            departamento= "-";
        }
        
        String nombreCalle=this.txtNombreCalle.getText();    
        String nombreBarrio=this.txtNombreBarrio.getText();
        
        gestor.tomarDomicilio(nombreCalle, numeroCalle, departamento, numeroPiso, nombreBarrio);   
    }
        
   private void tomarSeleccionTipoProveedor(){
       Lista tiposDeProveedor=new Lista();
        for(int i=0;i<this.tblTipoProveedor.getRowCount();i++){
         Boolean seleccionado=(Boolean)this.tblTipoProveedor.getValueAt(i, 1);
         
         boolean valor=false;
         
         if(seleccionado!=null){valor=seleccionado.booleanValue();}

         if(valor){
          String nombreTipoProveedor=(String)this.tblTipoProveedor.getValueAt(i, 0);
          tiposDeProveedor.insertarUltimo(nombreTipoProveedor);   
         }         
            
        }
             
        gestor.tomarTipoProveedor(tiposDeProveedor);   
    }
    
  private void tomarSeleccionCondicionIVA(){
    String condicionIva=(String)this.cmbCondicionIVA.getSelectedItem();
    gestor.tomarCondicionIva(condicionIva);   
    }
   
    public void mostrarPaises(Lista paises){
        Iterador iteradorPaises=paises.crearIterador();
        
        this.cmbPaisDomicilio.removeAllItems();
                
        while(iteradorPaises.siguiente()){
          Pais pais=(Pais) iteradorPaises.getElementoActual();
          
          this.cmbPaisDomicilio.addItem(pais.getNombre());
         
        }
    
    }
    
    public void habilitarCamposDatos(){
       this.tblTipoProveedor.setEnabled(true);
       this.cmbCondicionIVA.setEnabled(true);
       this.cmbPaisDomicilio.setEnabled(true);
       this.cmbProvinciaDomicilio.setEnabled(true);
       this.cmbCiudadDomicilio.setEnabled(true);
       this.txtRazonSocial.setEnabled(true);
       this.txtNombreCalle.setEnabled(true);
       this.txtNombreBarrio.setEnabled(true);
       this.txtNroCalle.setEnabled(true);
       this.chbDepartamento.setEnabled(true);
       this.txtTelefono1.setEnabled(true);
       this.txtTelefono2.setEnabled(true);
       this.txtTelefono3.setEnabled(true);
       this.txtCaracteristica1.setEnabled(true);
       this.txtCaracteristica2.setEnabled(true);
       this.txtCaracteristica3.setEnabled(true);
       this.cmbTipoTelefono1.setEnabled(true);
       this.cmbTipoTelefono2.setEnabled(true);
       this.cmbTipoTelefono3.setEnabled(true);
       this.txtNroIngresoBruto.setEnabled(true);
       this.btnAsignarProductos.setEnabled(true);
       this.btnRegistrarProveedor.setEnabled(true);
       this.btnRegistrarRepresentante.setEnabled(true);
    }
    
    private void btnComprobarNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprobarNumeroActionPerformed
       this.tomarIngresoNroCUIT();
    }//GEN-LAST:event_btnComprobarNumeroActionPerformed

    private void cmbPaisDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaisDomicilioActionPerformed
   
        String nomPais=(String) this.cmbPaisDomicilio.getSelectedItem(); 
            if(nomPais!=null){
        this.gestor.getProvincias(nomPais);
 } 

}//GEN-LAST:event_cmbPaisDomicilioActionPerformed

    private void cmbPaisDomicilioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPaisDomicilioMouseClicked

        
}//GEN-LAST:event_cmbPaisDomicilioMouseClicked

    private void cmbCiudadDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCiudadDomicilioActionPerformed
        
}//GEN-LAST:event_cmbCiudadDomicilioActionPerformed

    private void cmbProvinciaDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvinciaDomicilioActionPerformed
   if(bandDomicilio==0){
       String nomProvincia=(String) this.cmbProvinciaDomicilio.getSelectedItem();
   
    this.gestor.getCiudades(nomProvincia);
    
     }       
      
}//GEN-LAST:event_cmbProvinciaDomicilioActionPerformed

    private void btnRegistrarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarProveedorActionPerformed
        datosCorrectos=true;
        this.tomarSeleccionTipoProveedor();
        
        if (this.datosCorrectos==true){ 
        this.tomarIngresoDomicilio();
        
        if (this.datosCorrectos==true){
        this.tomarIngresoNroCUIT();
        
        if (this.datosCorrectos==true){
        this.tomarIngresoNroIngresoBruto();
        
        if (this.datosCorrectos==true){
        this.tomarIngresoRazonSocial();
        
        if (this.datosCorrectos==true){
        this.tomarSeleccionPaisSeleccionado();
        
        if (this.datosCorrectos==true){
        this.tomarSeleccionProvinciaSeleccionada();
        
        if (this.datosCorrectos==true){
        this.tomarSeleccionCiudadSeleccionada();
        
        if (this.datosCorrectos==true){
        this.tomarSeleccionCondicionIVA();    
        
        if (this.datosCorrectos==true){
        this.tomarIngresoTelefono1();
        
        if (this.datosCorrectos==true){
        this.tomarIngresoTelefono2();
        
        if (this.datosCorrectos==true){
        this.tomarIngresoTelefono3();
        
        //if (this.datosCorrectos==true){
        //this.tomarIngresoTelefonos(); 
        
        if (this.datosCorrectos==true){
      
            try {
                  this.tomarConfirmacionNuevoProveedor();
                 } 
           catch (Exception ex) 
           {
             Logger.getLogger(PllaRegistrarProveedor.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        }
        }
        }
        }    
        }
        }
        }
        } 
        }
        }
        }
    }
   
    }//GEN-LAST:event_btnRegistrarProveedorActionPerformed

    private void btnRegistrarProveedorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarProveedorMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarProveedorMouseReleased

    private void cmbPaisDomicilioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPaisDomicilioMouseReleased

}//GEN-LAST:event_cmbPaisDomicilioMouseReleased

    private void txtTelefono2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefono2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefono2ActionPerformed
    
    private void tomarConfirmacionNuevoProveedor() throws Exception{
    int confirmacion = Mensaje.mensaje_Consulta_YES_NO_OPTION("¿Desea confirmar la registración?", "Confirmación de Proveedor", Mensaje.TIPO_INTERROGACION);
    
    gestor.tomarConfirmacion(confirmacion);
    }      
            
    private void tomarIngresoTelefono1(){
      /* 
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
       
        gestor.tomarNumero1(caracteristica, numero,tipoTelefono);   
   */ }
   
   private void tomarIngresoTelefono2(){
    /*    if( this.txtCaracteristica2.getText().trim().length()!=0 && this.txtTelefono2.getText().trim().length()==0 )
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
       
        gestor.tomarNumero2(caracteristica, numero,tipoTelefono);   
  */  }   
   
     private void tomarIngresoTelefono3(){
     /*  if( this.txtCaracteristica3.getText().trim().length()!=0 && this.txtTelefono3.getText().trim().length()==0 )
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
       
        gestor.tomarNumero3(caracteristica, numero,tipoTelefono);   
 */   } 
    
    private void tomarSeleccionPaisSeleccionado(){
    String pais=(String)this.cmbPaisDomicilio.getSelectedItem();
        
    gestor.tomarPaisSeleccionado(pais);   
   
    }
    
    
    private void tomarSeleccionPais(String pais){
                      
        gestor.tomarPais(pais);
    }
   
    
    private void tomarSeleccionProvinciaSeleccionada(){
    String provincia=(String)this.cmbProvinciaDomicilio.getSelectedItem();
        
    gestor.tomarProvinciaSeleccionada(provincia);   
   
    }
    
    
    private void tomarSeleccionProvincia(String provincia){
        gestor.tomarProvincia(provincia);
    }
    
    private void tomarSeleccionCiudadSeleccionada(){
    String ciudad=(String)this.cmbCiudadDomicilio.getSelectedItem();
        
    gestor.tomarCiudadSeleccionada(ciudad);   
   
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarProductos;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnComprobarNumero;
    private javax.swing.JButton btnRegistrarProveedor;
    private javax.swing.JButton btnRegistrarRepresentante;
    private javax.swing.JCheckBox chbDepartamento;
    private javax.swing.JComboBox cmbCiudadDomicilio;
    private javax.swing.JComboBox cmbCondicionIVA;
    private javax.swing.JComboBox cmbPaisDomicilio;
    private javax.swing.JComboBox cmbProvinciaDomicilio;
    private javax.swing.JComboBox cmbTipoTelefono1;
    private javax.swing.JComboBox cmbTipoTelefono2;
    private javax.swing.JComboBox cmbTipoTelefono3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPDatosDomicilio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBarrio;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblNombreCalle;
    private javax.swing.JLabel lblNroCalle;
    private javax.swing.JLabel lblNroIngresoBruto;
    private javax.swing.JLabel lblNroPiso;
    private javax.swing.JLabel lblNumeroCuit;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblProvincia;
    private javax.swing.JLabel lblRazonSocial;
    private javax.swing.JLabel lblTelefono3;
    private javax.swing.JLabel lblTelefonos;
    private javax.swing.JLabel lblTipoProveedor;
    private javax.swing.JSeparator spDatosDepartamento;
    private javax.swing.JSeparator spDatosPais;
    private javax.swing.JTable tblTipoProveedor;
    private javax.swing.JTextField txtCaracteristica1;
    private javax.swing.JTextField txtCaracteristica2;
    private javax.swing.JTextField txtCaracteristica3;
    private javax.swing.JTextField txtDepartamento;
    private javax.swing.JTextField txtNombreBarrio;
    private javax.swing.JTextField txtNombreCalle;
    private javax.swing.JTextField txtNroCalle;
    private javax.swing.JTextField txtNroIngresoBruto;
    private javax.swing.JTextField txtNumeroCuit;
    private javax.swing.JTextField txtNumeroPiso;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTelefono2;
    private javax.swing.JTextField txtTelefono3;
    // End of variables declaration//GEN-END:variables
    
}
