/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.presentacion;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
