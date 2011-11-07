/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Nino
 */
public class Calculos {

    public static double mayor(double a, double b, double c) {
        if (a > b) {
            if (a > c) {
                return a;
            } else {
                return c;
            }
        } else {
            if (b > c) {
                return b;
            } else {
                return c;
            }
        }
    }

    public static double menor(double a, double b, double c) {
        if (a < b) {
            if (a < c) {
                return a;
            } else {
                return c;
            }
        } else {
            if (b < c) {
                return b;
            } else {
                return c;
            }
        }
    }

    public static double medio(double a, double b, double c) {
        if (a < b) {
            if (a > c) {
                return a;
            } else {
                return c;
            }
        } else {
            if (b > c) {
                return b;
            } else {
                return c;
            }
        }
    }

    public static int calcularCantidadMateriaPrima(int capacidadMatPrima, int cantPiezas) {
        if (capacidadMatPrima == 0) {
            return -1;
        }
        double resto = cantPiezas % capacidadMatPrima;
        if (resto == 0) {
            return cantPiezas / capacidadMatPrima;
        } else {
            return (cantPiezas / capacidadMatPrima) + 1;
        }
    }

    //-1: cancelar
    public static int calcularCapacidadMateriaPrima(double altoMatPrima, double anchoMatPrima, double largoMatPrima, double altoPieza, double anchoPieza, double largoPieza, String nombrePieza, String nombreMatPrima) {
        double volumenPieza = altoPieza * anchoPieza * largoPieza;
        double volumenMatPrima = altoMatPrima * anchoMatPrima * largoMatPrima;
        int respuesta = -1;
        if (volumenPieza > volumenMatPrima) {
            respuesta = JOptionPane.showConfirmDialog(null, "El volumen de la Pieza '" + nombrePieza + "' es mayor que el volumen de la materia prima '" + nombreMatPrima + "' seleccionada, desea continuar?", "ATENCIÓN", JOptionPane.OK_CANCEL_OPTION);
        }
        if (respuesta == JOptionPane.CANCEL_OPTION) {
            return -1;
        }

        //calculo los valores max med y min de la pieza y materia prima, donde los valores de la pieza
        //tienen que ser iguales o menores a los respectivos valores de la materia prima.
        double maxMatPrima = Calculos.mayor(altoMatPrima, anchoMatPrima, largoMatPrima);
        double minMatPrima = Calculos.menor(altoMatPrima, anchoMatPrima, largoMatPrima);
        double medMatPrima = Calculos.medio(altoMatPrima, anchoMatPrima, largoMatPrima);
        double maxPieza = Calculos.mayor(altoPieza, anchoPieza, largoPieza);
        double minPieza = Calculos.menor(altoPieza, anchoPieza, largoPieza);
        double medPieza = Calculos.medio(altoPieza, anchoPieza, largoPieza);

        if (maxPieza > maxMatPrima || medPieza > medMatPrima || minPieza > minMatPrima) {
            respuesta = JOptionPane.showConfirmDialog(null, "Las dimensiones de la Pieza '" + nombrePieza + "' son mayores que la materia prima '" + nombreMatPrima + "' seleccionada, desea continuar?", "ATENCIÓN", JOptionPane.OK_CANCEL_OPTION);
        }
        if (respuesta == JOptionPane.CANCEL_OPTION) {
            return -1;
        }

        int entranEnMax = (int) (maxMatPrima / maxPieza);
        int entranEnMed = (int) (medMatPrima / medPieza);
        int entranEnMin = (int) (minMatPrima / minPieza);

        int entranTotal = entranEnMax * entranEnMed * entranEnMin;

        return entranTotal;
    }

    public static Date calcularDuracionPiezaXEtapa(Date duracionEstimada, double alto, double ancho, double largo) {
        double volumen = alto * ancho * largo;

        int horas = duracionEstimada.getHours();
        int minutos = duracionEstimada.getMinutes();
        int segundos = duracionEstimada.getSeconds();

        horas = (int) (horas * volumen);
        minutos = (int) (minutos * volumen);
        segundos = (int) (segundos * volumen);

        Date duracion = (Date) duracionEstimada.clone();
        duracion.setHours(horas);
        duracion.setMinutes(minutos);
        duracion.setSeconds(segundos);

        //duracion=Fecha.diferenciaEnSegundosMinutosHoras(duracionEstimada, duracion);

        return duracion;
    }

    public static Date calcularDuracionPiezaXProcesoCalidad(Date duracionestimada) {
        return duracionestimada;
    }

    public static Calendar calcularFechaInicio(int horaInicioJornada, int horaFinJornada, Calendar inicio) {
        if (horaInicioJornada > inicio.get(Calendar.HOUR_OF_DAY)) {
            inicio.set(Calendar.HOUR_OF_DAY, horaInicioJornada);
            inicio.set(Calendar.MINUTE, 0);
            inicio.set(Calendar.SECOND, 0);
        }
        if (horaFinJornada < sumar(inicio.get(Calendar.HOUR_OF_DAY), minutosEnProporcion(inicio.get(Calendar.MINUTE)))) {
            inicio.add(Calendar.DAY_OF_YEAR, 1);
            inicio.set(Calendar.HOUR_OF_DAY, horaInicioJornada);
            inicio.set(Calendar.MINUTE, 0);
            inicio.set(Calendar.SECOND, 0);
        }
        if (horaInicioJornada > inicio.get(Calendar.HOUR_OF_DAY) || horaFinJornada < inicio.get(Calendar.HOUR_OF_DAY)) {
            inicio = calcularFechaInicio(horaInicioJornada, horaFinJornada, inicio);
        }
        return inicio;
    }

    public static float minutosEnProporcion(int minutos) {
        return (float) (minutos / 60f);
    }

    public static float restar(float a, float b) {
        return a - b;
    }

    public static float sumar(float a, float b) {
        return a + b;
    }

    public static GregorianCalendar calcularFechaFin(int horaInicioJornada, int horaFinJornada, GregorianCalendar fin) {

//        fin.add(Calendar.MINUTE, Jornada.MINUTOS_ENTRE_ETAPAS);
        System.out.println("Horas de fin: " + fin.get(Calendar.HOUR_OF_DAY));
        System.out.println("Hora inicio jornada: " + horaInicioJornada);
        System.out.println("Hora fin jornada: " + horaFinJornada);
        if (horaInicioJornada > fin.get(Calendar.HOUR_OF_DAY)) {
            System.out.println("if(horaInicioJornada > fin.get(Calendar.HOUR_OF_DAY))");
            int hora = fin.get(Calendar.HOUR_OF_DAY);
            int horaAM = (horaFinJornada - 12);
            int dif = horaAM - hora;
            int horasfaltantes = dif >= 0 ? 12 - dif : 12 + Math.abs(dif);
            fin.set(Calendar.HOUR_OF_DAY, horaInicioJornada + horasfaltantes);
        }
        if (horaFinJornada < sumar(fin.get(Calendar.HOUR_OF_DAY), minutosEnProporcion(fin.get(Calendar.MINUTE)))) {
            System.out.println("if(horaFinJornada < fin.get(Calendar.HOUR_OF_DAY))");
//            System.out.println("Horas de fin: " + fin.get(Calendar.HOUR_OF_DAY));
            int dif = fin.get(Calendar.HOUR_OF_DAY) - horaFinJornada;
            int horasfaltantes = dif;
            fin.add(Calendar.DAY_OF_YEAR, 1);
            fin.set(Calendar.HOUR_OF_DAY, horaInicioJornada + horasfaltantes);
        }
        if (horaInicioJornada > fin.get(Calendar.HOUR_OF_DAY) || horaFinJornada < fin.get(Calendar.HOUR_OF_DAY)) {
            System.out.println("if(horaInicioJornada > fin.get(Calendar.HOUR_OF_DAY) || horaFinJornada < fin.get(Calendar.HOUR_OF_DAY))");
            //            System.out.println("Horas de fin: " + fin.get(Calendar.HOUR_OF_DAY));
            fin = calcularFechaFin(horaInicioJornada, horaFinJornada, fin);
        }
        return fin;
    }

    public static GregorianCalendar calcularFechaFin(int horaInicioJornada, int horaFinJornada, Calendar inicio, int horas, int minutos) {
        System.out.println("calcularFechaFin(int horaInicioJornada, int horaFinJornada, Calendar inicio, int horas, int minutos) - INICIO: " + inicio.getTime());
        GregorianCalendar fin = new GregorianCalendar();
        fin.setTime(inicio.getTime());
        fin.add(Calendar.HOUR_OF_DAY, horas);
        fin.add(Calendar.MINUTE, minutos);
        System.out.println("calcularFechaFin(int horaInicioJornada, int horaFinJornada, Calendar inicio, int horas, int minutos): ADD HORAS MINUTOS: " + fin.getTime());
        fin = Calculos.calcularFechaFin(horaInicioJornada, horaFinJornada, fin);
        System.out.println("calcularFechaFin(int horaInicioJornada, int horaFinJornada, Calendar inicio, int horas, int minutos) - FIN: " + fin.getTime());
        return fin;
    }
}
