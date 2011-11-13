/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarProcesoCalidad.java
 *
 * Created on 25/05/2010, 09:43:22
 */

package metalsoft.presentacion;

import java.awt.Graphics;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.text.StyledEditorKit.ItalicAction;
import metalsoft.datos.jpa.entity.Procesocalidad;
import metalsoft.negocio.gestores.GestorProcesoCalidad;
import metalsoft.negocio.gestores.NumerosAMostrar;
import metalsoft.util.Combo;
import metalsoft.util.EnumOpcionesABM;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Nino
 */
public class RegistrarProcesoCalidad extends javax.swing.JDialog {

    /** Creates new form RegistrarProcesoCalidad */
    private EnumOpcionesABM opcion;
    private GestorProcesoCalidad gestor;
    private Procesocalidad procesoModificar;
    
    public RegistrarProcesoCalidad() {
        super(Principal.getVtnPrincipal());
        initComponents();
        addListeners();
        gestor = new GestorProcesoCalidad();
        gestor.cargarComboAccion(cmbAccion);
        gestor.cargarComboTipoMaquina(cmbTipoDeMaquina);
        enableComponents(false);
        
        botones.getBtnEliminar().setEnabled(false);
        botones.getBtnGuardar().setEnabled(false);
        botones.getBtnModificar().setEnabled(false);
    }
    
    private void enableComponents(boolean b){
        txtEspecificaciones.setEnabled(b);
        txtHerramientas.setEnabled(b);
        txtTolerancia.setEnabled(b);
        txtnombre.setEnabled(b);
        cmbAccion.setEnabled(b);
        cmbTipoDeMaquina.setEnabled(b);
        jsphoras.setEnabled(b);
        jspMin.setEnabled(b);
    }
    
    public void limpiarCampos(){
        txtEspecificaciones.setText("");
        txtHerramientas.setText("");
        txtTolerancia.setText("");
        txtnombre.setText("");
        cmbAccion.setSelectedIndex(0);
        cmbTipoDeMaquina.setSelectedIndex(0);
        jsphoras.setValue(0);
        jspMin.setValue(0);
        lblNroProceso.setText("...");
    }
    
    public void cargarDatos(){
        Calendar c= Calendar.getInstance();
        c.setTime(procesoModificar.getDuracionestimada());
        
        txtEspecificaciones.setText(procesoModificar.getEspecificacion());
        txtHerramientas.setText(procesoModificar.getHerramienta());
        txtTolerancia.setText(procesoModificar.getTolerancia());
        txtnombre.setText(procesoModificar.getNombre());
        Combo.setItemComboSeleccionado(cmbAccion,procesoModificar.getAccioncalidad().getIdaccioncalidad());
        //cmbTipoDeMaquina.setSelectedIndex(0);
        jsphoras.setValue(c.get(Calendar.HOUR));
        jspMin.setValue(c.get(Calendar.MINUTE));
        lblNroProceso.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PROCESO_CALIDAD, procesoModificar.getNroproceso().longValue()));
    
    }
    private void addListeners(){
        addListenerBtnNuevo();
        addListenerBtnGuardar();
        addListenerBtnModificar();
        addListenerBtnBuscar();
        addListenerBtnSalir();
    }
    
    private void addListenerBtnSalir() {
        botones.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt)
    {
        this.dispose();
    }
    
    private void addListenerBtnNuevo() {
        botones.getBtnNuevo().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
    }
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt)
    {
        opcion=EnumOpcionesABM.NUEVO;
        lblNroProceso.setText(NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PROCESO_CALIDAD, gestor.nuevoNumeroProceso()));
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
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt)
    {
        long id;
        if(opcion==EnumOpcionesABM.NUEVO)
        {
            id=gestor.guardarProceso(this.nuevoProceso());
            if(id>-1){
                JOptionPane.showMessageDialog(this, "Se Guardó la siguiente Materia Prima: "+txtnombre.getText());
                enableComponents(false);
                botones.getBtnGuardar().setEnabled(false);
                botones.getBtnModificar().setEnabled(false);
                botones.getBtnEliminar().setEnabled(false);
            }
            else JOptionPane.showMessageDialog(this, "Los datos no se pudieron guardar");
        }

        if(opcion==EnumOpcionesABM.MODIFICAR)
        {
            id=gestor.modificarProceso(procesoModificar);
            if(id>-1){
                JOptionPane.showMessageDialog(this, "Se modifico la siguiente Materia Prima: "+txtnombre.getText());
                enableComponents(false);
                botones.getBtnGuardar().setEnabled(false);
                botones.getBtnModificar().setEnabled(false);
                botones.getBtnEliminar().setEnabled(false);
            }
            else JOptionPane.showMessageDialog(this, "Los datos no se pudieron modificar");
        }
        limpiarCampos();
    }
    private void addListenerBtnModificar() {
        botones.getBtnModificar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
    }
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt)
    {
        opcion=EnumOpcionesABM.MODIFICAR;
        enableComponents(true);
        botones.getBtnGuardar().setEnabled(true);
        botones.getBtnModificar().setEnabled(false);
        botones.getBtnEliminar().setEnabled(false);
    }

    public Procesocalidad getProcesoModificar() {
        return procesoModificar;
    }

    public void setProcesoModificar(Procesocalidad procesoModificar) {
        this.procesoModificar = procesoModificar;
    }

    private void addListenerBtnBuscar() {
        botones.getBtnBuscar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
    }
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt)
    {
        ProcesoCalidad_Buscar buscar=new ProcesoCalidad_Buscar(this);
        buscar.setVentana(this);
        JFrameManager.centrarYMostrarVentana(buscar);
    }
    
    private Procesocalidad nuevoProceso(){
        Calendar c= Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, (Integer)jsphoras.getValue());
        c.set(Calendar.MINUTE, (Integer)jspMin.getValue());
        c.set(Calendar.SECOND, 0);
        Procesocalidad pc=new Procesocalidad();
        pc.setEspecificacion(txtEspecificaciones.getText());
        pc.setHerramienta(txtHerramientas.getText());
        pc.setTolerancia(txtTolerancia.getText());
        pc.setNombre(txtnombre.getText());
        pc.setAccioncalidad(gestor.getAccionCalidad(Long.parseLong(((ItemCombo)cmbAccion.getSelectedItem()).getId())));
        //pc.setTipoMaquina(gestor.getAccionCalidad(Long.parseLong(((ItemCombo)cmbTipoDeMaquina.getSelectedItem()).getId())));
        pc.setNroproceso(BigInteger.valueOf(gestor.nuevoNumeroProceso()));
        pc.setDuracionestimada(c.getTime());
        return pc;
    }

    private Procesocalidad modificarProceso(){
        Calendar c= Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, (Integer)jsphoras.getValue());
        c.set(Calendar.MINUTE, (Integer)jspMin.getValue());
        c.set(Calendar.SECOND, 0);
        
        procesoModificar.setEspecificacion(txtEspecificaciones.getText());
        procesoModificar.setHerramienta(txtHerramientas.getText());
        procesoModificar.setTolerancia(txtTolerancia.getText());
        procesoModificar.setNombre(txtnombre.getText());
        procesoModificar.setAccioncalidad(gestor.getAccionCalidad(Long.parseLong(((ItemCombo)cmbAccion.getSelectedItem()).getId())));
        //pc.setTipoMaquina(gestor.getAccionCalidad(Long.parseLong(((ItemCombo)cmbTipoDeMaquina.getSelectedItem()).getId())));
        procesoModificar.setNroproceso(BigInteger.valueOf(gestor.nuevoNumeroProceso()));
        procesoModificar.setDuracionestimada(c.getTime());
        return procesoModificar;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHerramientas = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        cmbTipoDeMaquina = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel(){

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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        minutos = new javax.swing.JLabel();
        txtTolerancia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtEspecificaciones = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        cmbAccion = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblNroProceso = new javax.swing.JLabel();
        jsphoras = new javax.swing.JSpinner();
        jspMin = new javax.swing.JSpinner();
        minutos1 = new javax.swing.JLabel();
        botones = new metalsoft.beans.ABM_Botones();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Proceso de Calidad");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Herramientas y Tipo de Máquina"));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Herramientas"));

        txtHerramientas.setColumns(20);
        txtHerramientas.setLineWrap(true);
        txtHerramientas.setRows(5);
        txtHerramientas.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtHerramientas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Tipo de Máquina:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTipoDeMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbTipoDeMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondopantallas2.png"))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel1.setText("Nombre:");

        jLabel3.setText("Duración Estimada:");

        minutos.setText("horas");

        jLabel7.setText("Tolerancia:");

        jLabel8.setText("Especificaciones:");

        txtEspecificaciones.setColumns(20);
        txtEspecificaciones.setLineWrap(true);
        txtEspecificaciones.setRows(5);
        txtEspecificaciones.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtEspecificaciones);

        jLabel4.setText("Acción:");

        jButton3.setText("Nuevo");

        jLabel2.setText("Nro. de Proceso:");

        lblNroProceso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNroProceso.setText("...");
        lblNroProceso.setEnabled(false);

        minutos1.setText("minutos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lblNroProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTolerancia, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jsphoras, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minutos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jspMin, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minutos1))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(55, 55, 55)
                                .addComponent(txtnombre))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(61, 61, 61)
                                .addComponent(cmbAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblNroProceso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jsphoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minutos)
                    .addComponent(jspMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minutos1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTolerancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(botones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 515, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botones, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarProcesoCalidad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.ABM_Botones botones;
    private javax.swing.JComboBox cmbAccion;
    private javax.swing.JComboBox cmbTipoDeMaquina;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JSpinner jspMin;
    private javax.swing.JSpinner jsphoras;
    private javax.swing.JLabel lblNroProceso;
    private javax.swing.JLabel minutos;
    private javax.swing.JLabel minutos1;
    private javax.swing.JTextArea txtEspecificaciones;
    private javax.swing.JTextArea txtHerramientas;
    private javax.swing.JTextField txtTolerancia;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables

}
