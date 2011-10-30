/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RegistrarIngresoCotizacionTrabajo.java
 *
 * Created on 27/08/2011, 17:56:47
 */
package metalsoft.presentacion;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Trabajotercerizado;
import metalsoft.negocio.gestores.GestorTrabajoTercerizado;
import metalsoft.util.Fecha;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory.UIColorHighlighter;

/**
 *
 * @author Vicky
 */
public class RegistrarIngresoCotizacionTrabajo extends javax.swing.JDialog {

    private List<Trabajotercerizado> filasTrabajosGenerados;
    private List<Trabajotercerizado> filasTrabajosEnviados;
    private GestorTrabajoTercerizado gestor;
    private List<Detalletrabajotercerizado> detalle;

    public List<Detalletrabajotercerizado> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Detalletrabajotercerizado> detalle) {
        this.detalle = detalle;
    }

    /** Creates new form RegistrarIngresoCotizacionTrabajo */
    public RegistrarIngresoCotizacionTrabajo() {
        super(Principal.getVtnPrincipal());
        initComponents();
        gestor = new GestorTrabajoTercerizado();
//        filasTrabajosGenerados=new LinkedList<Trabajotercerizado>();
//        filasTrabajosEnviados=new LinkedList<Trabajotercerizado>();
        filasTrabajosGenerados = gestor.obtenerTrabajosGenerados();
        tblTrabajosGenerados.updateUI();
        filasTrabajosEnviados = gestor.obtenerTrabajosEnviados();
        tblTrabajosEnviados.updateUI();
        addListeners();
        setearTablas();
        btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
        btnenviar.setEnabled(false);

    }

    private void addListeners() {
        addListenerBtnSeleccionarPedido();
    }

    private void addListenerBtnSeleccionarPedido() {
        btnSeleccionar1.getBtnSeleccionar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionar1BeanActionPerformed(evt);
            }
        });
    }

    private void btnSeleccionar1BeanActionPerformed(java.awt.event.ActionEvent evt) {

        Trabajotercerizado trab = filasTrabajosEnviados.get(tblTrabajosEnviados.getSelectedRow());
        ActualizarMontosDetalleTrabajoTercerizado actualizacion = null;
        try {
            actualizacion = (ActualizarMontosDetalleTrabajoTercerizado) JFrameManager.crearVentana(ActualizarMontosDetalleTrabajoTercerizado.class.getName());
            actualizacion.setVentana(this);
            actualizacion.setGestor(gestor);
            actualizacion.setTrabajo(trab);
            actualizacion.setListaDetalle(gestor.buscarDetalleTrabajoTercerizado(trab.getIdtrabajo()));

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ABMEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ABMEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ABMEmpresaMantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modificarTrabajoTercerizado(Trabajotercerizado trab) {
        long result = 0;
        if (detalle != null) {

            trab.setEstado(gestor.buscarEstadoPresupuestado());
            trab.setFechadelingresocotizacion(Fecha.fechaActualDate());
            double monto = 0d;

            for (Detalletrabajotercerizado d : detalle) {
                monto = +d.getMontoparcial();
            }
            trab.setMontototal(monto);
            result = gestor.modificarTrabajoTercerizado(trab, detalle);


            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Los datos se han guardado correctamente!");
                filasTrabajosEnviados = gestor.obtenerTrabajosEnviados();
                tblTrabajosEnviados.updateUI();
                btnSeleccionar1.getBtnSeleccionar().setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Los datos no han podido ser guardados");
            }
        }
    }

    private void setearTablas() {
        //Trabajos Generados
        tblTrabajosGenerados.setModel(new PedidoCotizacionGeneradoTableModel());
        tblTrabajosGenerados.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblTrabajosGenerados.setShowHorizontalLines(false);
        tblTrabajosGenerados.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblTrabajosGenerados.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
        //Trabajos Enviados
        tblTrabajosEnviados.setModel(new PedidoCotizacionEnviadoTableModel());
        tblTrabajosEnviados.setColumnControlVisible(true);
        /* On supprime les traits des lignes et des colonnes */
        tblTrabajosEnviados.setShowHorizontalLines(false);
        tblTrabajosEnviados.setShowVerticalLines(false);
        /* On dit de surligner une ligne sur deux */
        tblTrabajosEnviados.setHighlighters(
                new UIColorHighlighter(HighlightPredicate.ODD));
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
        tblTrabajosGenerados = new org.jdesktop.swingx.JXTable();
        jLabel1 = new javax.swing.JLabel();
        btnenviar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTrabajosEnviados = new org.jdesktop.swingx.JXTable();
        btnSeleccionar1 = new metalsoft.beans.BtnSeleccionar();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel(){

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
        setTitle("Regitrar Envio / Llegada de Cotizacion");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enviar Pedidos De Cotizacion de Trabajo a Empresa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblTrabajosGenerados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTrabajosGeneradosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTrabajosGenerados);

        jLabel1.setText("Seleccionar Pedido de Cotizacion de Trabajo Generado:");

        btnenviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metalsoft/presentacion/img/seleccionar.png"))); // NOI18N
        btnenviar.setText("Enviar a Empresa");
        btnenviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(btnenviar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnenviar))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar el ingreso de los Pedidos de Cotizacion de Trabajo enviados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblTrabajosEnviados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTrabajosEnviadosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTrabajosEnviados);

        jLabel2.setText("Seleccionar Pedido de Cotizacion de Trabajo Generado:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                    .addComponent(btnSeleccionar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenviarActionPerformed
        long result = 0;
        if (tblTrabajosGenerados.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido!");
            return;
        }
        Trabajotercerizado trab = filasTrabajosGenerados.get(tblTrabajosGenerados.getSelectedRow());
        trab.setEstado(gestor.buscarEstadoEnEsperaPresupuesto());
        trab.setFechaenvioaempresa(Fecha.fechaActualDate());
        result = gestor.modificarTrabajoTercerizado(trab);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "El Pedido de Cotización ha sido enviado correctamente");
            filasTrabajosGenerados = gestor.obtenerTrabajosGenerados();
            tblTrabajosGenerados.updateUI();
            filasTrabajosEnviados = gestor.obtenerTrabajosEnviados();
            tblTrabajosEnviados.updateUI();
            btnenviar.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "El Pedido de Cotización NO podido ser enviado");

        }
    }//GEN-LAST:event_btnenviarActionPerformed

    private void tblTrabajosGeneradosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTrabajosGeneradosMouseClicked
        // TODO add your handling code here:
        btnenviar.setEnabled(true);
    }//GEN-LAST:event_tblTrabajosGeneradosMouseClicked

    private void tblTrabajosEnviadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTrabajosEnviadosMouseClicked
        // TODO add your handling code here:
        btnSeleccionar1.getBtnSeleccionar().setEnabled(true);
    }//GEN-LAST:event_tblTrabajosEnviadosMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarIngresoCotizacionTrabajo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private metalsoft.beans.BtnSeleccionar btnSeleccionar1;
    private javax.swing.JButton btnenviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXTable tblTrabajosEnviados;
    private org.jdesktop.swingx.JXTable tblTrabajosGenerados;
    // End of variables declaration//GEN-END:variables

    class PedidoCotizacionGeneradoTableModel extends AbstractTableModel {

        String[] columnNames = {"nro. Trab tercerizado",
            "Fecha Pedido Cotizacion",
            "Empresa",
            "Pedido",
            "Estado"};

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (!filasTrabajosGenerados.isEmpty()) {
                Trabajotercerizado trab = filasTrabajosGenerados.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return String.valueOf(trab.getNrotrabajotercerizado());
                    case 1:
                        return String.valueOf(trab.getFechapedidocotizacion());
                    case 2:
                        return String.valueOf(trab.getEmpresa().getRazonsocial());
                    case 3:
                        return String.valueOf(trab.getPedido().getNropedido());
                    case 4:
                        return String.valueOf(trab.getEstado().getNombre());
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
            if (filasTrabajosGenerados != null) {
                return filasTrabajosGenerados.size();
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

    class PedidoCotizacionEnviadoTableModel extends AbstractTableModel {

        String[] columnNames = {"nro. Trab tercerizado",
            "Fecha Pedido Cotizacion",
            "Empresa",
            "Pedido",
            "Fecha de Envio A Empresa",
            "Estado"};

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (!filasTrabajosEnviados.isEmpty()) {
                Trabajotercerizado trab = filasTrabajosEnviados.get(rowIndex);
//      Object[] df=filas.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return String.valueOf(trab.getNrotrabajotercerizado());
                    case 1:
                        return String.valueOf(trab.getFechapedidocotizacion());
                    case 2:
                        return trab.getEmpresa().getRazonsocial();
                    case 3:
                        return String.valueOf(trab.getPedido().getNropedido());
                    case 4:
                        return String.valueOf(trab.getFechaenvioaempresa());
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
            if (filasTrabajosEnviados != null) {
                return filasTrabajosEnviados.size();
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
