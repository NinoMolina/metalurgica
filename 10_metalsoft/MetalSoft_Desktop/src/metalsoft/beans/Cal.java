/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.beans;


import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class Cal
extends JFrame {

    private JLabel lblCalendar;
    private JPanel pnlDays;
        private JLabel lblDays[];

    private final String days[] = {
        "Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"
    };

    private final String months[] = {
        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
        "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    };

    private final int daysMonth[] = {
        31, 28, 31, 30, 31, 30,
        31, 31, 30, 31, 30, 31
    };

    public Cal(int month, int year) {
        int offset = 0;
        int numDays = 0;
        int today = 0;

        /**
         * GregorianCalendar es una subclase de Calendar, por lo que nos
         * proporciona toda la funcionalidad de esta clase y ademas nos brinda
         * algunos metodos mas. En este caso estamos iniciando dos
         * GregorianCalendar utilizando diferentes constructores.
         *
         * currCal se inicia con Calendar.getInstance() que nos devuelve un
         *         Calendar fijado en el dia de hoy.
         * cal     se inicia con el constructor que recibe la fecha
         */
        GregorianCalendar currCal = (GregorianCalendar) Calendar.getInstance();
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);

        /**
         * En caso de que el mes del calendario que iniciamos con la fecha
         * actual sea el mismo que el que iniciamos con la fecha pedida
         * entonces cambiamos el dia de hoy
         */
        if (currCal.get(Calendar.MONTH) == cal.get(Calendar.MONTH) &&
            currCal.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) {

            today = cal.get(Calendar.DAY_OF_MONTH);
        }

        /**
         * Obtengo el numero de dias del arreglo
         */
        numDays = daysMonth[month];

        /** Si es biciesto y es febrero le sumo uno al numero de dias */
        if (month == Calendar.FEBRUARY && cal.isLeapYear(year)) {
            numDays++;
        }

        /**
         * Obtengo el numero de dias que van a ir en blanco.
         * Al llamar el metodo get de la siguiente manera
         * get(Calendar.DAY_OF_WEEK) nos va a devolver un 0 para domingo, un 1
         * para lunes y un 6 para sabado, por lo que es el numero de dias que
         * debemos dejar en blanco directamente
         */
        offset = cal.get(Calendar.DAY_OF_WEEK);

        /**
         * El GridLayout en realidad no le hace caso mas que a uno de los dos
         * parametros que recibe y en el otro se expande hasta que entren todos
         * los controles que se agruegen. Para indicarle cual de los dos debe
         * despreciar ponemos un cero, lo que interpreta Java como expande este
         * lado hasta que entren todos los controles.
         */
        this.setLayout(new BorderLayout());
        pnlDays = new JPanel(new GridLayout(0, 7));

        lblCalendar = new JLabel(months[month] + " " + year);
//        lblCalendar.setIcon(
//                new ImageIcon(Main.class.getResource("date.png")));
        lblCalendar.setFont(new Font("Arial", Font.BOLD, 28));
        getContentPane().add(lblCalendar, BorderLayout.NORTH);

        lblDays = new JLabel[numDays];

        /**
         * Utilizo n para que nuestros amigos franceses vean que la semana
         * empieza en lunes y nosotros veamos que empieza en domingo.
         */
        int n = 0;
        if (cal.getFirstDayOfWeek() == Calendar.MONDAY)
            n++;

        for (int i = 0; i < days.length; i++) {
            /**
             * Utilizo mod para que me regrese numeros del 0 al 6 que esten
             * dentro del arreglo de dias
             */
            JLabel lblDayName = new JLabel( days[n++ % 7] );
            lblDayName.setFont( new Font("Arial", Font.BOLD, 13) );
            lblDayName.setHorizontalAlignment(JLabel.CENTER);
            pnlDays.add(lblDayName);
        }

        /** Por cada dia en que todavia no empieza el mes */
        for (int i = 0; i < offset - 1; i++) {
            /** Agrego un JLabel en blanco */
            pnlDays.add(new JLabel());
        }

        /** Por cada dia del mes */
        for (int i = 0; i < numDays; i++) {
            /** Creo un nuevo JLabel que tenga como texto el valor de i + 1,
             * como i va desde cero hasta el numero de dias menos uno al
             * sumarle uno a i obtenemos el numero de dia real.
             */
            lblDays[i] = new JLabel( String.valueOf(i + 1) );
            lblDays[i].setHorizontalAlignment(JLabel.CENTER);
            /**
             * Podemos agregar un borde utilizando el metodo setBorder y la
             * clase BorderFactory, que ademas de este metodo tiene varios
             * otros metodos que crean bordes diferentes.
             */
            lblDays[i].setBorder(
                    BorderFactory.createLineBorder(Color.BLACK));

            /** Si es el dia de hoy */
            if (i + 1 == today) {
                /** Cambio el color a rojo */
                lblDays[i].setOpaque(true);
                lblDays[i].setBackground(new Color(255, 200, 200));
            }

            /** Si es mi cumple */
            if (i + 1 == 23 &&
                month == Calendar.SEPTEMBER) {

                /** Pongo un pastel y cambio el color a verde */
//                lblDays[i].setIcon(new ImageIcon(
//                        Main.class.getResource("cake.png")));

                lblDays[i].setOpaque(true);
                lblDays[i].setBackground(new Color(200, 255, 200));
            }

            pnlDays.add(lblDays[i]);
        }
        /** Cambio el color del panel a blanco para que parezca calendario */
        pnlDays.setBackground(Color.WHITE);
        getContentPane().add(pnlDays, BorderLayout.CENTER);

        this.setSize(550, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Cal(Calendar.SEPTEMBER, 2007);
    }

}

