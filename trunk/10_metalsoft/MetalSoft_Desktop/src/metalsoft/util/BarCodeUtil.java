/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.util;

/**
 *
 * @author Nino
 */
public class BarCodeUtil {

    public static final String TAG_COMIENZO = "<$cod>";
    public static final String TAG_FINAL = "</$cod>";

    public static String generarCodigo(String valor) {
        String cod = "";

        cod = TAG_COMIENZO + valor + TAG_FINAL;

        return cod;
    }
}
