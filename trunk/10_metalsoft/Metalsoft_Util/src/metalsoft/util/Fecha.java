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


    public static void main(String args[])
    {
        
        String s=parseToHourMinuteSecond(new Date());
        System.out.println(s);
        
    }
}
