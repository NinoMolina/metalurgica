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

import datechooser.view.CalendarPane;
import datechooser.view.ComboNavigatePane;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JPanel;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class RegistrarDiaNoLaboral extends javax.swing.JFrame {

    private GregorianCalendar fechaActual;

    /** Creates new form Calendario */
    public RegistrarDiaNoLaboral() {
        initComponents();
        armarCalendario();
    }

    private void armarCalendario() {
        fechaActual = (GregorianCalendar) Fecha.fechaActualCalendar();
        setMesAnio(fechaActual.get(Calendar.YEAR));
        setEnableCalendars(fechaActual.get(Calendar.MONTH));
    }

    private void setMesAnio(int anio) {
        dcpEnero.setCurrent(Fecha.parseToCalendar("01/01/"+anio));
        dcpFebrero.setCurrent(Fecha.parseToCalendar("01/02/"+anio));
        dcpMarzo.setCurrent(Fecha.parseToCalendar("01/03/"+anio));
        dcpAbril.setCurrent(Fecha.parseToCalendar("01/04/"+anio));
        dcpMayo.setCurrent(Fecha.parseToCalendar("01/05/"+anio));
        dcpJunio.setCurrent(Fecha.parseToCalendar("01/06/"+anio));
        dcpJulio.setCurrent(Fecha.parseToCalendar("01/07/"+anio));
        dcpAgosto.setCurrent(Fecha.parseToCalendar("01/08/"+anio));
        dcpSeptiembre.setCurrent(Fecha.parseToCalendar("01/09/"+anio));
        dcpOctubre.setCurrent(Fecha.parseToCalendar("01/10/"+anio));
        dcpNoviembre.setCurrent(Fecha.parseToCalendar("01/11/"+anio));

        CalendarPane cp=(CalendarPane) dcpNoviembre.getComponent(0);
//        System.out.println(cp.getComponentCount());
//        cp.getComponent(0).setEnabled(false);
//        cp.getComponent(1).setEnabled(false);
        ComboNavigatePane cnp=(ComboNavigatePane) cp.getComponent(1);
        //desabilita la X
        //cnp.getComponent(1).setEnabled(false);
        //desabilita el combo del mes
        ((JPanel)cnp.getComponent(0)).getComponent(0).setEnabled(false);
        //desabilita el spinner del anio
        ((JPanel)cnp.getComponent(0)).getComponent(1).setEnabled(false);
//        System.out.println(((JPanel)cnp.getComponent(0)).getComponent(0));
        
        dcpDiciembre.setCurrent(Fecha.parseToCalendar("01/12/"+anio));
    }

    private void setEnableCalendars(int mes) {

        switch (mes) {
            case Calendar.JANUARY:
                break;
            case Calendar.FEBRUARY:
                dcpEnero.setEnabled(false);
                break;
            case Calendar.MARCH:
                dcpEnero.setEnabled(false);
                dcpFebrero.setEnabled(false);
                break;
            case Calendar.APRIL:
                dcpEnero.setEnabled(false);
                dcpFebrero.setEnabled(false);
                dcpMarzo.setEnabled(false);
                break;
            case Calendar.MAY:
                dcpEnero.setEnabled(false);
                dcpFebrero.setEnabled(false);
                dcpMarzo.setEnabled(false);
                dcpAbril.setEnabled(false);
                break;
            case Calendar.JUNE:
                dcpEnero.setEnabled(false);
                dcpFebrero.setEnabled(false);
                dcpMarzo.setEnabled(false);
                dcpAbril.setEnabled(false);
                dcpMayo.setEnabled(false);
                break;
            case Calendar.JULY:
                dcpEnero.setEnabled(false);
                dcpFebrero.setEnabled(false);
                dcpMarzo.setEnabled(false);
                dcpAbril.setEnabled(false);
                dcpMayo.setEnabled(false);
                dcpJunio.setEnabled(false);
                break;
            case Calendar.AUGUST:
                dcpEnero.setEnabled(false);
                dcpFebrero.setEnabled(false);
                dcpMarzo.setEnabled(false);
                dcpAbril.setEnabled(false);
                dcpMayo.setEnabled(false);
                dcpJunio.setEnabled(false);
                dcpJulio.setEnabled(false);
                break;
            case Calendar.SEPTEMBER:
                dcpEnero.setEnabled(false);
                dcpFebrero.setEnabled(false);
                dcpMarzo.setEnabled(false);
                dcpAbril.setEnabled(false);
                dcpMayo.setEnabled(false);
                dcpJunio.setEnabled(false);
                dcpJulio.setEnabled(false);
                dcpAgosto.setEnabled(false);
                break;
            case Calendar.OCTOBER:
                dcpEnero.setEnabled(false);
                dcpFebrero.setEnabled(false);
                dcpMarzo.setEnabled(false);
                dcpAbril.setEnabled(false);
                dcpMayo.setEnabled(false);
                dcpJunio.setEnabled(false);
                dcpJulio.setEnabled(false);
                dcpAgosto.setEnabled(false);
                dcpSeptiembre.setEnabled(false);
                break;
            case Calendar.NOVEMBER:
                dcpEnero.setEnabled(false);
                dcpFebrero.setEnabled(false);
                dcpMarzo.setEnabled(false);
                dcpAbril.setEnabled(false);
                dcpMayo.setEnabled(false);
                dcpJunio.setEnabled(false);
                dcpJulio.setEnabled(false);
                dcpAgosto.setEnabled(false);
                dcpSeptiembre.setEnabled(false);
                dcpOctubre.setEnabled(false);
                break;
            case Calendar.DECEMBER:
                dcpEnero.setEnabled(false);
                dcpFebrero.setEnabled(false);
                dcpMarzo.setEnabled(false);
                dcpAbril.setEnabled(false);
                dcpMayo.setEnabled(false);
                dcpJunio.setEnabled(false);
                dcpJulio.setEnabled(false);
                dcpAgosto.setEnabled(false);
                dcpSeptiembre.setEnabled(false);
                dcpOctubre.setEnabled(false);
                dcpNoviembre.setEnabled(false);
                break;
            default:
                break;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        dcpEnero = new datechooser.beans.DateChooserPanel();
        dcpFebrero = new datechooser.beans.DateChooserPanel();
        dcpMarzo = new datechooser.beans.DateChooserPanel();
        dcpAbril = new datechooser.beans.DateChooserPanel();
        dcpMayo = new datechooser.beans.DateChooserPanel();
        dcpJunio = new datechooser.beans.DateChooserPanel();
        dcpJulio = new datechooser.beans.DateChooserPanel();
        dcpAgosto = new datechooser.beans.DateChooserPanel();
        dcpSeptiembre = new datechooser.beans.DateChooserPanel();
        dcpOctubre = new datechooser.beans.DateChooserPanel();
        dcpNoviembre = new datechooser.beans.DateChooserPanel();
        dcpDiciembre = new datechooser.beans.DateChooserPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Año:");

        btnSiguiente.setText(">>");

        btnAnterior.setText("<<");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 387, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(324, 324, 324)
                .addComponent(btnSiguiente)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSiguiente)
                    .addComponent(btnAnterior)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        dcpEnero.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dcpEnero.setCalendarBackground(new java.awt.Color(204, 255, 204));
    try {
        dcpEnero.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }
    dcpEnero.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

    try {
        dcpFebrero.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }

    try {
        dcpMarzo.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }

    try {
        dcpAbril.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }

    try {
        dcpMayo.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }

    try {
        dcpJunio.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }

    try {
        dcpJulio.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }

    try {
        dcpAgosto.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }

    try {
        dcpSeptiembre.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }

    try {
        dcpOctubre.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }

    try {
        dcpNoviembre.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }
    dcpNoviembre.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
        public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
            dcpNoviembreOnSelectionChange(evt);
        }
    });

    dcpDiciembre.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
try {
    dcpDiciembre.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(dcpEnero, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dcpFebrero, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dcpMarzo, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dcpAbril, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(dcpMayo, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dcpJunio, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dcpJulio, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dcpAgosto, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(dcpSeptiembre, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dcpOctubre, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dcpNoviembre, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dcpDiciembre, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(dcpAbril, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dcpMarzo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dcpFebrero, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dcpEnero, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dcpMayo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dcpJunio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dcpJulio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dcpAgosto, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(dcpSeptiembre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dcpOctubre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dcpNoviembre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dcpDiciembre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(44, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dcpNoviembreOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dcpNoviembreOnSelectionChange
        CalendarPane cp=(CalendarPane) dcpNoviembre.getComponent(0);
//        System.out.println(cp.getComponentCount());
//        cp.getComponent(0).setEnabled(false);
//        cp.getComponent(1).setEnabled(false);
        ComboNavigatePane cnp=(ComboNavigatePane) cp.getComponent(1);
        //desabilita la X
        //cnp.getComponent(1).setEnabled(false);
        //desabilita el combo del mes
        ((JPanel)cnp.getComponent(0)).getComponent(0).setEnabled(false);
        //desabilita el spinner del anio
        ((JPanel)cnp.getComponent(0)).getComponent(1).setEnabled(false);
//        System.out.println(((JPanel)cnp.getComponent(0)).getComponent(0));
    }//GEN-LAST:event_dcpNoviembreOnSelectionChange

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
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnSiguiente;
    private datechooser.beans.DateChooserPanel dcpAbril;
    private datechooser.beans.DateChooserPanel dcpAgosto;
    private datechooser.beans.DateChooserPanel dcpDiciembre;
    private datechooser.beans.DateChooserPanel dcpEnero;
    private datechooser.beans.DateChooserPanel dcpFebrero;
    private datechooser.beans.DateChooserPanel dcpJulio;
    private datechooser.beans.DateChooserPanel dcpJunio;
    private datechooser.beans.DateChooserPanel dcpMarzo;
    private datechooser.beans.DateChooserPanel dcpMayo;
    private datechooser.beans.DateChooserPanel dcpNoviembre;
    private datechooser.beans.DateChooserPanel dcpOctubre;
    private datechooser.beans.DateChooserPanel dcpSeptiembre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables
}
