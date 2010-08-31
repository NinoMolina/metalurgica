/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nino
 */
public class Fecha {

    public static final long MILISEGUNDOS_POR_DIA = 86400000;
    public static final long MILISEGUNDOS_POR_HORA = 3600000;
    public static final long MILISEGUNDOS_POR_MINUTO = 60000;
    public static final long MILISEGUNDOS_POR_SEGUNDO = 1000;
    
    public static String fechaActual()
    {
        Date fecha=new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
    }

    public static Calendar fechaActualCalendar()
    {
        return Calendar.getInstance();
    }


    public static String fechaHoraMinutoSegundoActual()
    {
        Date fecha=new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        
        return formato.format(fecha);
    }

    public static String parseToString(Date fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
    }

    public static Calendar parseToCalendar(Date fecha)
    {
        Calendar c=new GregorianCalendar();
        c.setTime(fecha);
        return c;
    }

    public static Calendar parseToCalendar(String fecha)
    {
        Date d=parseToDate(fecha);
        Calendar c=new GregorianCalendar();
        c.setTime(d);
        return c;
    }

    /*
     * @param -fecha: la fecha a convertir a String
     * @param -formatoFecha: el formato en String a utilizar
     * para convertir la fecha, por ejemplo: "dd/MM/yyyy", para usar sólo la fecha.
     * "hh:mm:ss", para obtener sólo la hora minuto y segundo. También se puede hacer cualquier
     * convinación de estos, por ejemplo: "'La fecha es 'dd/MM/yyyy' y la hora es 'hh:mm:ss".
     * @return la fecha convertida a String segun el formato indicado
     */
    public static String parseToString(Date fecha, String formatoFecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat(formatoFecha);
        return formato.format(fecha);
    }

    public static String parseToString(Date fecha, SimpleDateFormat formato)
    {
        return formato.format(fecha);
    }

    public static Date parseToDate(String fecha)
    {
        Date f=null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            f = formato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Fecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public static String parseToString(long fecha)
    {
        String s="";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            s=formato.format(new Date(fecha));
        } catch (Exception ex) {
            Logger.getLogger(Fecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public static Date parseToDate(long fecha)
    {
        Date f=new Date(fecha);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            String s=formato.format(f);
            f=formato.parse(s);
        } catch (Exception ex) {
            Logger.getLogger(Fecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public static Date parseToDate(String fecha, String formatoFecha)
    {
        Date f=null;
        SimpleDateFormat formato = new SimpleDateFormat(formatoFecha);
        try {
            f = formato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Fecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public static Date parseToDate(String fecha, SimpleDateFormat formato)
    {
        Date f=null;
        
        try {
            f = formato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Fecha.class.getName()).log(Level.SEVERE, null, ex);
        }


        return f;
    }
    public static java.sql.Date parseToDateSQL(Date date)
    {
        return new java.sql.Date(date.getTime());
    }
    public static java.sql.Time parseToTimeSQL(Date date)
    {
        return new java.sql.Time(date.getTime());
    }

    public static Date parseToHourMinuteSecond(String fecha)
    {
        Date f=null;
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
        try {
            f = formato.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Fecha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public static String parseToHourMinuteSecond(Date fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
        return formato.format(fecha);
    }

    public static int diferenciaEnDias(Date fechaInicial, Date fechaFinal) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        String fechaFinalString = df.format(fechaFinal);
        try {
            fechaInicial = df.parse(fechaInicioString);
            fechaFinal = df.parse(fechaFinalString);
        }
        catch (ParseException ex) {
        }

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / MILISEGUNDOS_POR_DIA);
        return ( (int) dias);
    }

//    public static int diferenciaEnMinutos(Date fechaInicial, Date fechaFinal) {
//
//        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
//        String fechaInicioString = df.format(fechaInicial);
//        String fechaFinalString = df.format(fechaFinal);
//        try {
//            fechaInicial = df.parse(fechaInicioString);
//            fechaFinal = df.parse(fechaFinalString);
//        }
//        catch (ParseException ex) {
//        }
//
//        long fechaInicialMs = fechaInicial.getTime();
//        long fechaFinalMs = fechaFinal.getTime();
//        long diferencia = fechaFinalMs - fechaInicialMs;
//        double dias = Math.floor(diferencia / MILISEGUNDOS_POR_MINUTO);
//        return ( (int) dias);
//    }
//
//    public static int diferenciaEnHoras(Date fechaInicial, Date fechaFinal) {
//
//        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
//        String fechaInicioString = df.format(fechaInicial);
//        String fechaFinalString = df.format(fechaFinal);
//        try {
//            fechaInicial = df.parse(fechaInicioString);
//            fechaFinal = df.parse(fechaFinalString);
//        }
//        catch (ParseException ex) {
//        }
//
//        long fechaInicialMs = fechaInicial.getTime();
//        long fechaFinalMs = fechaFinal.getTime();
//        long diferencia = fechaFinalMs - fechaInicialMs;
//        double dias = Math.floor(diferencia / MILISEGUNDOS_POR_HORA);
//        return ( (int) dias);
//    }
//
//    public static int diferenciaEnSegundos(Date fechaInicial, Date fechaFinal) {
//
//        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
//        String fechaInicioString = df.format(fechaInicial);
//        String fechaFinalString = df.format(fechaFinal);
//        try {
//            fechaInicial = df.parse(fechaInicioString);
//            fechaFinal = df.parse(fechaFinalString);
//        }
//        catch (ParseException ex) {
//        }
//
//        long fechaInicialMs = fechaInicial.getTime();
//        long fechaFinalMs = fechaFinal.getTime();
//        long diferencia = fechaFinalMs - fechaInicialMs;
//        double dias = Math.floor(diferencia / MILISEGUNDOS_POR_SEGUNDO);
//        return ( (int) dias);
//    }

    public static void main(String args[])
    {
        
        String s=fechaActual();
        Date d=parseToDate(s);
        java.sql.Date dsql=parseToDateSQL(d);
        System.out.println(dsql);
        Date d2=new Date(dsql.getTime());

        System.out.println(d);
        
    }
}
