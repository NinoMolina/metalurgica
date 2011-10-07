/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ActualizarMontosDetalleTrabajoTercerizado.java
 *
 * Created on 27/08/2011, 23:58:39
 */

package metalsoft.presentacion;

import java.beans.beancontext.BeanContext;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Trabajotercerizado;
import metalsoft.negocio.gestores.GestorTrabajoTercerizado;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class ActualizarMontosDetalleTrabajoTercerizado extends javax.swing.JDialog {

    private Trabajotercerizado trabajo;
    private List<Detalletrabajotercerizado> listaDetalle;
    private GestorTrabajoTercerizado gestor;
    private RegistrarIngresoCotizacionTrabajo ventana;

    public RegistrarIngresoCotizacionTrabajo getVentana() {
        return ventana;
    }

    public void setVentana(RegistrarIngresoCotizacionTrabajo ventana) {
        this.ventana = ventana;
    }

    public GestorTrabajoTercerizado getGestor() {
        return gestor;
    }

    public void setGestor(GestorTrabajoTercerizado gestor) {
        this.gestor = gestor;
    }


    /** Creates new form ActualizarMontosDetalleTrabajoTercerizado */
    public ActualizarMontosDetalleTrabajoTercerizado() {
        initComponents();
        addListeners();
        tblDetalleTrabajoTercerizado.setModel(new DetalleTableModel());
        tblDetalleTrabajoTercerizado.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblDetalleTrabajoTercerizado.setShowHorizontalLines(false);
        tblDetalleTrabajoTercerizado.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblDetalleTrabajoTercerizado.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        btnSeleccionar1.getBtnSeleccionar().setEnabled(false);

    }
    private void addListeners() {
        addListenerBtnSeleccionarPedido();
        addListenerBtnGuardar();
        addListenerBtnSalir();
    }

    private void addListenerBtnSeleccionarPedido() {
        btnSeleccionar1.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionar1BeanActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionar1BeanActionPerformed(java.awt.event.ActionEvent evt) {
        String mens = JOptionPane.showInputDialog(this, "Ingrese el monto total de la cotización");
        Double monto = 0d;
        long result=0;
        if (mens.compareTo("") != 0) {
            try {
                monto = Double.parseDouble(mens);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un valor numerico valido");
                return;
            }
            listaDetalle.get(tblDetalleTrabajoTercerizado.getSelectedRow()).setMontoparcial(monto);
            tblDetalleTrabajoTercerizado.updateUI();
            btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
            
        } else {
            btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
            JOptionPane.showMessageDialog(this, "Debe ingresar un valor numerico valido");
        }
    }
    private void addListenerBtnGuardar() {
        btnGuardar1.getBtnGuardar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarBeanActionPerformed(evt);
            }
        });
    }

    private void btnGuardarBeanActionPerformed(java.awt.event.ActionEvent evt) {
        boolean result=false;
        for(Detalletrabajotercerizado detalle : listaDetalle){
            if(detalle.getMontoparcial()!=null){
                result=true;
            }else{
                result=false;
            }
        }
        if(result) {
            ventana.setDetalle(listaDetalle);
        }else{
            JOptionPane.showMessageDialog(this, "NO se han Guardado los cambios: todos los detalles deben tener monto");
        }
    }
    private void addListenerBtnSalir() {
        beanBtnSalir.getBtnSalir().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        ventana.modificarTrabajoTercerizado(trabajo);
        
    }
    public List<Detalletrabajotercerizado> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<Detalletrabajotercerizado> listaDetalle) {
        this.listaDetalle = listaDetalle;
        tblDetalleTrabajoTercerizado.updateUI();
    }

    public Trabajotercerizado getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajotercerizado trabajo) {
        this.trabajo = trabajo;
        cargarDatosTrabajo();
    }
    public void cargarDatosTrabajo(){
        lblEmpresa.setText(trabajo.getEmpresa().getRazonsocial());
        lblEstado.setText(trabajo.getEstado().getNombre());
        lblNroPedido.setText("PED-"+String.valueOf(trabajo.getPedido().getNropedido()));
        lblNroTrabajoTercerizado.setText("TRAB-"+String.valueOf(trabajo.getNrotrabajotercerizado()));
        dccFechaEnvio.setDate(trabajo.getFechaenvioaempresa());
        dccFechaPedido.setDate(trabajo.getFechapedidocotizacion());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleTrabajoTercerizado = new org.jdesktop.swingx.JXTable();
        btnSeleccionar1 = new metalsoft.beans.BtnSeleccionar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblNroTrabajoTercerizado = new javax.swing.JLabel();
        lblNroPedido = new javax.swing.JLabel();
        lblEmpresa = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        dccFechaPedido = new com.toedter.calendar.JDateChooser();
        dccFechaEnvio = new com.toedter.calendar.JDateChooser();
        btnGuardar1 = new metalsoft.beans.BtnGuardar();
        beanBtnSalir = new metalsoft.beans.BtnSalirr();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblDetalleTrabajoTercerizado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleTrabajoTercerizadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetalleTrabajoTercerizado);

        jLabel1.setText("Nro. Trabajo Tercerizado:");

        jLabel2.setText("Nro. Pedido:");

        jLabel3.setText("Fecha de Pedido Cotizacion:");

        jLabel4.setText("Empresa:");

        jLabel5.setText("Estado:");

        jLabel6.setText("Fecha de Envio a Empresa:");

        dccFechaPedido.setEnabled(false);

        dccFechaEnvio.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSeleccionar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNroTrabajoTercerizado, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNroPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dccFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dccFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblNroTrabajoTercerizado, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(lblNroPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(dccFechaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(lblEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(dccFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(beanBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(beanBtnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDetalleTrabajoTercerizadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleTrabajoTercerizadoMouseClicked
        btnSeleccionar1.getBtnSeleccionar().setEnabled(true);
    }//GEN-LAST:event_tblDetalleTrabajoTercerizadoMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActualizarMontosDetalleTrabajoTercerizado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnSalirr beanBtnSalir;
    private metalsoft.beans.BtnGuardar btnGuardar1;
    private metalsoft.beans.BtnSeleccionar btnSeleccionar1;
    private com.toedter.calendar.JDateChooser dccFechaEnvio;
    private com.toedter.calendar.JDateChooser dccFechaPedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEmpresa;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNroPedido;
    private javax.swing.JLabel lblNroTrabajoTercerizado;
    private org.jdesktop.swingx.JXTable tblDetalleTrabajoTercerizado;
    // End of variables declaration//GEN-END:variables

    class DetalleTableModel extends AbstractTableModel {

        String[] columnNames = {"Detalle nro",
            "Monto Parcial",
            "Pieza",
            "Proceso",
            "cantidad"};

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (!listaDetalle.isEmpty()) {
                Detalletrabajotercerizado trab = listaDetalle.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return String.valueOf(trab.getIddetalle());
                    case 1:
                        return String.valueOf(trab.getMontoparcial());
                    case 2:
                        return gestor.buscarPieza(trab.getPieza().longValue()).getNombre();
                    case 3:
                        return trab.getProceso().getNombre();
                    case 4:
                        return String.valueOf(trab.getCantidad());
                    case 5:
                        return trab.getEstado().getNombre();
                    default:
                        return null;
                }
            } else {
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
            if (listaDetalle != null) {
                return listaDetalle.size();
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
