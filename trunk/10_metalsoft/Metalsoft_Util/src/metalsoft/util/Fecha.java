/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static String parseToString(Date fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha);
    }

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
}
