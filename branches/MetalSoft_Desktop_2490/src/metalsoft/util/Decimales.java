/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.util;

import java.text.DecimalFormat;

/**
 *
 * @author Nino
 */
public class Decimales {

    public static double parseToDouble(String valor)
    {
        valor=valor.replace(',','.');
        return Double.parseDouble(valor);
    }
    public static String con2Decimales(double valor)
    {
        DecimalFormat df=new DecimalFormat("0.00");
        return df.format(valor);
    }
}
