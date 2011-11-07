/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Calendario.java
 *
 * Created on 05/10/2010, 11:50:52
 */
package metalsoft.presentacion;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.imageio.ImageIO;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import metalsoft.datos.dbobject.CalendarioDB;
import metalsoft.datos.jpa.entity.Calendario;
import metalsoft.negocio.gestores.GestorCalendario;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class RegistrarDiaNoLaboral extends javax.swing.JDialog {

    private GregorianCalendar fechaActual;
    private GestorCalendario gestor;
    private int yearAnterior = -1;
    private HashMap<GregorianCalendar, CalendarioDB> hashDiasNoLaborales;

    /** Creates new form Calendario */
    public RegistrarDiaNoLaboral() {
        super(Principal.getVtnPrincipal());
        initComponents();
        addListenerComboMonth();
        addListenerSpinnerYear();
        fechaActual = (GregorianCalendar) Fecha.fechaActualCalendar();
        yearAnterior = fechaActual.get(Calendar.YEAR);
        gestor = new GestorCalendario();
//        armarCalendario();
//        mostrarDiasNoLaborales();
        setearCalendarioMesActual();
        mostrarDiasNoLaborales();
    }

    private void addListenerComboMonth() {

        JComboBox cmb = (JComboBox) monthChooser.getComboBox();
        cmb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ComboMonthActionPerformed(e);
            }
        });
    }

    private void addListenerSpinnerYear() {

//        JSpinner cmb = (JSpinner) yearChooser.getSpinner();

        yearChooser.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                SpinnerYearChangedListener(e);
            }
        });
    }

    private void SpinnerYearChangedListener(ChangeEvent e) {
        int year = Integer.parseInt(String.valueOf(yearChooser.getValue()));
        int mes = monthChooser.getMonth();
        GregorianCalendar c = new GregorianCalendar(year, mes, 1);
        calendario.setFirstDisplayedDay(c.getTime());
    }

    private void ComboMonthActionPerformed(java.awt.event.ActionEvent e) {
        int mes = monthChooser.getMonth();
        GregorianCalendar c = new GregorianCalendar(Integer.parseInt(String.valueOf(yearChooser.getValue())), mes, 1);
//        System.out.println(Fecha.parseToString(c.getTime()));
        calendario.setFirstDisplayedDay(c.getTime());

    }

    private void setearCalendarioMesActual() {
        monthChooser.setMonth(fechaActual.get(Calendar.MONTH));
        yearChooser.setValue(fechaActual.get(Calendar.YEAR));
        int mes = monthChooser.getMonth();
        int year = Integer.parseInt(String.valueOf(yearChooser.getValue()));
        calendario.setSelectionDate(fechaActual.getTime());
        calendario.clearSelection();
        calendario.setDayForeground(1, Color.red);
        calendario.setFlaggedDayForeground(Color.red);
    }

    private void mostrarDiasNoLaborales() {
        hashDiasNoLaborales = gestor.buscarDiasMayoresActual(fechaActual.getTime());
        if (hashDiasNoLaborales == null) {
            JOptionPane.showMessageDialog(this, "No hay ningún día no laboral asignado para el futuro...");
            return;
        }
        Collection<CalendarioDB> collection = hashDiasNoLaborales.values();
        for (CalendarioDB calendarioDB : collection) {
            if (calendarioDB.getAnio() == Integer.parseInt(String.valueOf(yearChooser.getValue())) && (calendarioDB.getMes() - 1) == monthChooser.getMonth()) {
                agregarACalendario(calendarioDB.getFecha());
            }
        }
    }

    private void agregarACalendario(Date fecha) {
        calendario.addFlaggedDates(fecha);
    }

//    private void agregarACalendario(int mes, Date fecha) {
//        switch ((mes - 1)) {
//            case Calendar.JANUARY:
//                dcpEnero.getSelectedPeriodSet().add(Fecha.parseToCalendar(fecha));
//
//                break;
//            case Calendar.FEBRUARY:
//                dcpFebrero.getSelectedPeriodSet().add(Fecha.parseToCalendar(fecha));
//                break;
//            case Calendar.MARCH:
//                dcpMarzo.getSelectedPeriodSet().add(Fecha.parseToCalendar(fecha));
//                break;
//            case Calendar.APRIL:
//                dcpAbril.getSelectedPeriodSet().add(Fecha.parseToCalendar(fecha));
//                break;
//            case Calendar.MAY:
//                dcpMayo.getSelectedPeriodSet().add(Fecha.parseToCalendar(fecha));
//                break;
//            case Calendar.JUNE:
//                dcpJunio.getSelectedPeriodSet().add(Fecha.parseToCalendar(fecha));
//                break;
//            case Calendar.JULY:
//                dcpJulio.getSelectedPeriodSet().add(Fecha.parseToCalendar(fecha));
//                break;
//            case Calendar.AUGUST:
//                dcpAgosto.getSelectedPeriodSet().add(Fecha.parseToCalendar(fecha));
//                break;
//            case Calendar.SEPTEMBER:
//                dcpSeptiembre.getSelectedPeriodSet().add(Fecha.parseToCalendar(fecha));
//                break;
//            case Calendar.OCTOBER:
////                dcpOctubre.getSelectedPeriodSet().add(Fecha.parseToCalendar(fecha));
//                break;
//            case Calendar.NOVEMBER:
//                dcpNoviembre.getSelectedPeriodSet().add(Fecha.parseToCalendar(fecha));
//
//                break;
//            case Calendar.DECEMBER:
////                dcpDiciembre.getSelectedPeriodSet().add(Fecha.parseToCalendar(fecha));
//                break;
//            default:
//                break;
//        }
//    }
//    private void armarCalendario() {
//        fechaActual = (GregorianCalendar) Fecha.fechaActualCalendar();
//        spnAnio.setValue(fechaActual.get(Calendar.YEAR));
//        setMesAnio(fechaActual.get(Calendar.YEAR));
//        setEnableCalendars(fechaActual.get(Calendar.MONTH));
//    }
//    private void setMesAnio(int anio) {
//        dcpEnero.setCurrent(Fecha.parseToCalendar("01/01/" + anio));
//        dcpFebrero.setCurrent(Fecha.parseToCalendar("01/02/" + anio));
//        dcpMarzo.setCurrent(Fecha.parseToCalendar("01/03/" + anio));
//        dcpAbril.setCurrent(Fecha.parseToCalendar("01/04/" + anio));
//        dcpMayo.setCurrent(Fecha.parseToCalendar("01/05/" + anio));
//        dcpJunio.setCurrent(Fecha.parseToCalendar("01/06/" + anio));
//        dcpJulio.setCurrent(Fecha.parseToCalendar("01/07/" + anio));
//        dcpAgosto.setCurrent(Fecha.parseToCalendar("01/08/" + anio));
//        dcpSeptiembre.setCurrent(Fecha.parseToCalendar("01/09/" + anio));
////        dcpOctubre.setCurrent(Fecha.parseToCalendar("01/10/" + anio));
//        dcpOctubre.setOpaque(true);
//        dcpOctubre.setDayForeground(1, Color.red);
//        dcpOctubre.setDaysOfTheWeekForeground(Color.GREEN);
//        dcpOctubre.setTodayBackground(Color.YELLOW);
//        Date[] d=new Date[2];
//        d[0]=Fecha.fechaActualCalendar().getTime();
//        d[1]=Fecha.parseToDate("13/10/2010");
//        dcpOctubre.setFlaggedDates(d);
//        dcpOctubre.setFlaggedDayForeground(Color.red);
//        dcpNoviembre.setCurrent(Fecha.parseToCalendar("01/11/" + anio));
//
//        CalendarPane cp = (CalendarPane) dcpNoviembre.getComponent(0);
////        System.out.println(cp.getComponentCount());
////        cp.getComponent(0).setEnabled(false);
////        cp.getComponent(1).setEnabled(false);
//        ComboNavigatePane cnp = (ComboNavigatePane) cp.getComponent(1);
//        //desabilita la X
//        //cnp.getComponent(1).setEnabled(false);
//        //desabilita el combo del mes
//        ((JPanel) cnp.getComponent(0)).getComponent(0).setEnabled(false);
//        //desabilita el spinner del anio
//        ((JPanel) cnp.getComponent(0)).getComponent(1).setEnabled(false);
//
//        dcpDiciembre.setMonth(Calendar.DECEMBER);
//        dcpDiciembre.setYear(anio);
//    }
//
//    private void setEnableCalendars(int mes) {
//
//        switch (mes) {
//            case Calendar.JANUARY:
//                dcpEnero.setMinDate(fechaActual);
//                break;
//            case Calendar.FEBRUARY:
//                dcpEnero.setEnabled(false);
//                dcpFebrero.setMinDate(fechaActual);
//                break;
//            case Calendar.MARCH:
//                dcpEnero.setEnabled(false);
//                dcpFebrero.setEnabled(false);
//                dcpMarzo.setMinDate(fechaActual);
//                break;
//            case Calendar.APRIL:
//                dcpEnero.setEnabled(false);
//                dcpFebrero.setEnabled(false);
//                dcpMarzo.setEnabled(false);
//                dcpAbril.setMinDate(fechaActual);
//                break;
//            case Calendar.MAY:
//                dcpEnero.setEnabled(false);
//                dcpFebrero.setEnabled(false);
//                dcpMarzo.setEnabled(false);
//                dcpAbril.setEnabled(false);
//                dcpMayo.setMinDate(fechaActual);
//                break;
//            case Calendar.JUNE:
//                dcpEnero.setEnabled(false);
//                dcpFebrero.setEnabled(false);
//                dcpMarzo.setEnabled(false);
//                dcpAbril.setEnabled(false);
//                dcpMayo.setEnabled(false);
//                dcpJunio.setMinDate(fechaActual);
//                break;
//            case Calendar.JULY:
//                dcpEnero.setEnabled(false);
//                dcpFebrero.setEnabled(false);
//                dcpMarzo.setEnabled(false);
//                dcpAbril.setEnabled(false);
//                dcpMayo.setEnabled(false);
//                dcpJunio.setEnabled(false);
//                dcpJulio.setMinDate(fechaActual);
//                break;
//            case Calendar.AUGUST:
//                dcpEnero.setEnabled(false);
//                dcpFebrero.setEnabled(false);
//                dcpMarzo.setEnabled(false);
//                dcpAbril.setEnabled(false);
//                dcpMayo.setEnabled(false);
//                dcpJunio.setEnabled(false);
//                dcpJulio.setEnabled(false);
//                dcpAgosto.setMinDate(fechaActual);
//                break;
//            case Calendar.SEPTEMBER:
//                dcpEnero.setEnabled(false);
//                dcpFebrero.setEnabled(false);
//                dcpMarzo.setEnabled(false);
//                dcpAbril.setEnabled(false);
//                dcpMayo.setEnabled(false);
//                dcpJunio.setEnabled(false);
//                dcpJulio.setEnabled(false);
//                dcpAgosto.setEnabled(false);
//                dcpSeptiembre.setMinDate(fechaActual);
//                break;
//            case Calendar.OCTOBER:
//                dcpEnero.setEnabled(false);
//                dcpFebrero.setEnabled(false);
//                dcpMarzo.setEnabled(false);
//                dcpAbril.setEnabled(false);
//                dcpMayo.setEnabled(false);
//                dcpJunio.setEnabled(false);
//                dcpJulio.setEnabled(false);
//                dcpAgosto.setEnabled(false);
//                dcpSeptiembre.setEnabled(false);
////                dcpOctubre.setMinDate(fechaActual);
//                break;
//            case Calendar.NOVEMBER:
//                dcpEnero.setEnabled(false);
//                dcpFebrero.setEnabled(false);
//                dcpMarzo.setEnabled(false);
//                dcpAbril.setEnabled(false);
//                dcpMayo.setEnabled(false);
//                dcpJunio.setEnabled(false);
//                dcpJulio.setEnabled(false);
//                dcpAgosto.setEnabled(false);
//                dcpSeptiembre.setEnabled(false);
//                dcpOctubre.setEnabled(false);
//                dcpNoviembre.setMinDate(fechaActual);
//                break;
//            case Calendar.DECEMBER:
//                dcpEnero.setEnabled(false);
//                dcpFebrero.setEnabled(false);
//                dcpMarzo.setEnabled(false);
//                dcpAbril.setEnabled(false);
//                dcpMayo.setEnabled(false);
//                dcpJunio.setEnabled(false);
//                dcpJulio.setEnabled(false);
//                dcpAgosto.setEnabled(false);
//                dcpSeptiembre.setEnabled(false);
//                dcpOctubre.setEnabled(false);
//                dcpNoviembre.setEnabled(false);
////                dcpDiciembre.setMinDate(fechaActual);
//                break;
//            default:
//                break;
//        }
//    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        calendario = new org.jdesktop.swingx.JXMonthView();
        monthChooser = new com.toedter.calendar.JMonthChooser();
        yearChooser = new javax.swing.JSpinner();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
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
        setTitle("Día No Laboral");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        calendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calendarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendario, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(monthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128)
                        .addComponent(yearChooser)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(monthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calendario, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
        );

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 278, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Date fechaSeleccionada = calendario.getSelectionDate();
        Calendar c = Fecha.parseToCalendar(fechaSeleccionada);
        int mes = c.get(Calendar.MONTH) + 1;
        int anio = c.get(Calendar.YEAR);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        Calendario cal=new Calendario();
        cal.setAnio(anio);
        cal.setDia(dia);
        cal.setMes(mes);
        cal.setFecha(fechaSeleccionada);
        cal.setTodoeldia(true);

        int resp = JOptionPane.showConfirmDialog(this, "Se agregará el día " + Fecha.parseToString(fechaSeleccionada) + " como día no laboral \nDesea continuar?");
        if (resp == JOptionPane.OK_OPTION) {
            gestor.guardarDiaNoLaboral(cal);
            calendario.addFlaggedDates(fechaSeleccionada);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void calendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calendarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_calendarioActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new RegistrarDiaNoLaboral().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private org.jdesktop.swingx.JXMonthView calendario;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JMonthChooser monthChooser;
    private javax.swing.JSpinner yearChooser;
    // End of variables declaration//GEN-END:variables
}
